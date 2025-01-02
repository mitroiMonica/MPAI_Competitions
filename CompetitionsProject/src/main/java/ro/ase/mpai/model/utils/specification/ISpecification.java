package ro.ase.mpai.model.utils.specification;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T item);
}
