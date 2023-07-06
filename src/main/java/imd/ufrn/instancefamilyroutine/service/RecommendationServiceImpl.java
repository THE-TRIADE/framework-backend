package imd.ufrn.instancefamilyroutine.service;

import imd.ufrn.framework.model.api.response.SpentResponse;
import imd.ufrn.framework.service.SpentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private SpentService spentService;

    public RecommendationServiceImpl(SpentService spentService) {
        this.spentService = spentService;
    }

    @Override
    public List<String> generateRecommendation(Long userId) {
        List<SpentResponse> spents = spentService.findSpentsByUserId(userId);

        double spentsLastMonth = getSpentsLastMonth(spents);
        double spentsCurrentMonth = getSpentsCurrentMonth(spents);

        double spentDifference = spentsCurrentMonth - spentsLastMonth;

        List<String> recommendations = new ArrayList<>();

        if(spentsCurrentMonth <= 0){
            recommendations.add("Fique Atento! Você ainda não realizou nenhum pagamento esse mês.");
            return recommendations;
        }

        if (spentDifference > 0) {
            recommendations.add("Fique Atento! Você gastou R$" + spentDifference/100 + " a mais no mês atual comparado ao mês anterior.");
        } else if (spentDifference < 0) {
            recommendations.add("Ótimo! Você economizou R$" + (-spentDifference/100) + " no mês atual comparado ao mês anterior.");
        } else {
            recommendations.add("Seus gastos se mantiveram iguais em relação ao mês anterior.");
        }

        if (spentsCurrentMonth > 300000) {
            recommendations.add("Cuidado! O total gasto neste mês já ultrapassou seu orçamento");
        } else {
            recommendations.add("Ótimo! O total gasto neste mês está dentro do seu orçamento.");
        }

        return recommendations;
    }

    private double getSpentsLastMonth(List<SpentResponse> spents) {
        double spentsLastMonth = 0.0;
        YearMonth lastMonth = YearMonth.now().minusMonths(1);

        for (SpentResponse spent : spents) {
            LocalDate spentDate = spent.getPaidOn().toLocalDate();

            YearMonth spentMonth = YearMonth.from(spentDate);

            if (spentMonth.equals(lastMonth)) {
                spentsLastMonth += spent.getValue();
            }
        }

        return spentsLastMonth;
    }

    private double getSpentsCurrentMonth(List<SpentResponse> spents) {
        double spentsCurrentMonth = 0.0;
        YearMonth currentMonth = YearMonth.now();

        for (SpentResponse spent : spents) {
            LocalDate spentDate = spent.getPaidOn().toLocalDate();

            YearMonth spentMonth = YearMonth.from(spentDate);

            if (spentMonth.equals(currentMonth)) {
                spentsCurrentMonth += spent.getValue();
            }
        }

        return spentsCurrentMonth;
    }
}
