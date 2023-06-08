package cart.ui.argument_resolver;

import cart.exception.InvalidPageException;
import cart.ui.paging.Page;
import cart.ui.paging.Pageable;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Pageable.class)
                && parameter.getParameterType().isAssignableFrom(Page.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory)
            throws Exception {
        try {
            String page = webRequest.getParameter("page");
            if (Objects.isNull(page) || Integer.parseInt(page) <= 0) {
                page = String.valueOf(Page.DEFAULT_PAGE);
            }
            String size = webRequest.getParameter("size");
            if (Objects.isNull(size) || Integer.parseInt(size) <= 0) {
                size = String.valueOf(Page.DEFAULT_SIZE);
            }
            return new Page(Integer.parseInt(page), Integer.parseInt(size));
        } catch (NumberFormatException e) {
            throw new InvalidPageException("page와 size는 양의 정수로 입력되어야합니다.");
        }
    }
}
