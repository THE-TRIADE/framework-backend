package imd.ufrn.familyroutine.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// TODO Ana Maria
@ExtendWith(SpringExtension.class)
public class GuardServiceTest {

    @Nested
    public class UpdateGuard {
        /*
         * Positivo simples - ID que existe e é um Long
         * Negativo com entradas válidas  - ID não existe, mas é um Long
         */
    }

    @Nested
    public class DeleteGuardById {
        /*
         * Positivo simples - ID que existe e é um Long
         * Negativo com entradas válidas  - ID não existe, mas é um Long
         */
    }

    @Nested
    public class DeleteAllGuards {
        /*
         * Positivo simples
         */
    }
}
