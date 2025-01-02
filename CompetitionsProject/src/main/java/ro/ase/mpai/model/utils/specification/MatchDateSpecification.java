package ro.ase.mpai.model.utils.specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchDateSpecification implements ISpecification<LocalDateTime> {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public MatchDateSpecification(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isSatisfiedBy(LocalDateTime time) {
        return time.isAfter(startDate.atStartOfDay()) && time.isBefore(endDate.atStartOfDay());
    }
}
