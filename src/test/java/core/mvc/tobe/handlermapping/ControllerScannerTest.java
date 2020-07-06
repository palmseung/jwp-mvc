package core.mvc.tobe.handlermapping;

import core.mvc.tobe.handler.HandlerExecutions;
import core.mvc.tobe.handlermapping.exception.ControllerScanException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ControllerScannerTest {
    @DisplayName("Controller 어노테이션이 붙은 Element를 스캔하여 HandlerExecution 형태로 반환")
    @Test
    void scanController() {
        //given
        Object basePackage = "core.mvc";

        //when
        HandlerExecutions handlerExecutions = ControllerScanner.scan(basePackage);

        //then
        int handlerCountInMyController = 3;
        assertThat(handlerExecutions.getHandlerExecutions()).hasSize(handlerCountInMyController);
    }

    @DisplayName("Controller 어노테이션이 붙은 Element가 없으면 예외 반환")
    @Test
    void scanControllerWhenControllerNotExist() {
        //given
        Object basePackage = "core.jdbc";

        //when, then
        assertThatThrownBy(() -> {
            HandlerExecutions handlerExecutions = ControllerScanner.scan(basePackage);
        }).isInstanceOf(ControllerScanException.class);
    }
}
