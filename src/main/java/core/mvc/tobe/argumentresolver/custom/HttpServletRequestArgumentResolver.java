package core.mvc.tobe.argumentresolver.custom;

import core.mvc.tobe.argumentresolver.MethodArgumentResolver;
import core.mvc.tobe.argumentresolver.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public class HttpServletRequestArgumentResolver implements MethodArgumentResolver {
    @Override
    public boolean support(MethodParameter methodParameter) {
        return methodParameter.getType().equals(HttpServletRequest.class);
    }

    @Override
    public Object resolve(MethodParameter methodParameter, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return request;
    }
}
