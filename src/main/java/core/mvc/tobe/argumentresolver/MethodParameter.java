package core.mvc.tobe.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.function.BiFunction;

public enum MethodParameter {
    PARAMETER((request, method) -> MethodParameterUtils.getParameters(request, method));

    private BiFunction<HttpServletRequest, Method, Object[]> expression;

    MethodParameter(BiFunction<HttpServletRequest, Method, Object[]> expression) {
        this.expression = expression;
    }

    public static Object[] getParameters(HttpServletRequest request, Method method){
        return PARAMETER.expression
                .apply(request, method);
    }
}