package imd.ufrn.framework.config;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import imd.ufrn.framework.model.ActivityAbstract;

public abstract class ActivityBatchScheduler<T extends ActivityAbstract, U>  {
    private final Logger logger = LoggerFactory.getLogger(ActivityBatchScheduler.class);
    private AtomicBoolean enabled = new AtomicBoolean(true);
    private AtomicInteger batchRunCounter = new AtomicInteger(0);
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private DataSource dataSource;

    public abstract U createRowMapperInstance();

    @Scheduled(fixedRate = 60000, initialDelay = 40000)
    public void launchJob() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        if (enabled.get()) {
            JobExecution jobExecution = jobLauncher.run(activityJob(jobRepository, transactionManager), 
                    new JobParametersBuilder().addLocalDateTime("launchDate", date).toJobParameters());
            batchRunCounter.incrementAndGet();
            logger.info("Batch job ends with status as " + jobExecution.getStatus());
        }
    }

    public void stop() {
        enabled.set(false);
    }

    public void start() {
        enabled.set(true);
    }

    @Bean
    public Job activityJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("activityJob", jobRepository)
                .start(checkActivityState(jobRepository, transactionManager))
                .build();
    }

    @Bean
    protected Step checkActivityState(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("checkActivityState", jobRepository)
                .<T,T> chunk(5, transactionManager)
                .reader(reader(dataSource))
                .processor(processor())
                .writer(writer(dataSource))
                .build();
    }

    public AtomicInteger getBatchRunCounter() {
        return batchRunCounter;
    }

    @Bean
    public ItemReader<T> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<T>()
            .name("activityItemReader")
            .verifyCursorPosition(false)
            .dataSource(dataSource)
            .sql("SELECT * FROM ACTIVITY")
            .rowMapper((RowMapper<T>) createRowMapperInstance())
            .build();
    }

    @Bean
    public ItemProcessor<T, T> processor() {
        return new ActivityItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<T> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<T>()
          .itemSqlParameterSourceProvider(parameterSourceProvider())
          .sql("UPDATE `activity` SET `state` = :state WHERE id = :id")
          .dataSource(dataSource)
          .build();
    }

    public BeanPropertyItemSqlParameterSourceProvider<T> parameterSourceProvider () {
        return new BeanPropertyItemSqlParameterSourceProvider<>() {

            @Override
            public SqlParameterSource createSqlParameterSource(T item) {
                return new BeanPropertySqlParameterSource(item) {
                    @Override
                    public Object getValue(String paramName) throws IllegalArgumentException {
                        Object value = super.getValue(paramName);
                        if (value instanceof Enum) {
                            return value.toString();
                        }
                        return value;
                    }
                };
            }
        };
    }
}
