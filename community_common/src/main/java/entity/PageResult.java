package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResult<T> {
    private long total;
    private List<T> rows;

    public static <T>PageResult<T> create(long total, List<T> rows) {
        return new PageResult<>(total, rows);
    }


}
