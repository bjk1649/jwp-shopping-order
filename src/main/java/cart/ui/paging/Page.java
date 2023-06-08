package cart.ui.paging;

import cart.exception.InvalidPageException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "페이징 정보")
public class Page {

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_SIZE = 10;
    public static final Page DEFAULT = new Page(DEFAULT_PAGE, DEFAULT_SIZE);

    @Schema(description = "조회할 페이지", defaultValue = "1", example = "1")
    private final int page;
    @Schema(description = "조회할 페이지의 사이즈", defaultValue = "10", example = "10")
    private final int size;

    public Page(final int page, final int size) {
        validate(page, size);
        this.page = page;
        this.size = size;
    }

    private void validate(final int page, final int size) {
        if (page <= 0) {
            throw new InvalidPageException("page 번호는 0 이하일 수 없습니다.");
        }
        if (size <= 0) {
            throw new InvalidPageException("page의 size는 0 이하일 수 없습니다.");
        }
    }

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return getOffset() + size;
    }
}
