package za.co.yellowfire.carat.web;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import za.co.yellowfire.carat.db.Dao;
import za.co.yellowfire.carat.db.DataAccessException;

import javax.faces.model.ListDataModel;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MemoryPageableDataModel<E> extends ListDataModel<E> {
    @Getter @Setter
    private int first;
    @Getter
    private int increment = 5;
    private int rows = 0;
    private Dao<E> dao;

    /**
     * <p>Construct a new {@link ListDataModel} wrapping the specified list.</p>
     * @param dao The data access object used to retrieve the list to be wrapped (if any)
     * @throws DataAccessException If the list could not be retrieved
     */
    public MemoryPageableDataModel(Dao<E> dao) throws DataAccessException {
        super();
        this.dao = dao;
        setWrappedData(dao.retrieve());
    }

    public int getRowCount() {
        final int remaining = super.getRowCount() - first;
        if (remaining > increment) {
            return increment;
        }
        return remaining;
    }

    public int getPageCount() {
        double inc = increment;
        return ((Number) Math.ceil(super.getRowCount() / inc)).intValue();
    }

    public int getCurrentPage() {
        return (first / increment) + 1;
    }

    public Integer[] getPages() {
        final int current = getCurrentPage();
        final int last = getPageCount();
        final List<Integer> pages = new ArrayList<>();

        log.info("Current:" + current + " Last:" + last);

        if (current > 1) {
            pages.add(current - 1);
        }
        pages.add(current);
        if (current < last) {
            pages.add(current + 1);
        }
        return pages.toArray(new Integer[pages.size()]);
    }

    public void onFirst() {
        onGoto(1);
    }

    public void onLast() {
        onGoto(getPageCount());
    }

    public String onNext() {
        if (first + increment <= super.getRowCount() - 1) {
            first = first + increment;
        } else {
            first = super.getRowCount() - 1 - increment;
        }
        log.info("next: first={} increment={} size={}", new Object[] {first, rows, super.getRowCount()});
        return null;
    }

    public String onPrevious() {
        if (first - increment <= 0) {
            first = 0;
        } else {
            first = first - increment;
        }
        log.info("next: first={} increment={} size={}", new Object[] {first, rows, super.getRowCount()});
        return null;
    }

    public String onGoto(Integer page) {
        first = (page - 1) * increment;
        return null;
    }

    public String onRefresh() throws DataAccessException {
        setWrappedData(dao.retrieve());
        return null;
    }

    public boolean isNextRendered() {
        return first + increment < super.getRowCount();
    }

    public boolean isPreviousRendered() {
        return first > 0;
    }
}
