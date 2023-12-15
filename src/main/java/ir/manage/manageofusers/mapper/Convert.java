package ir.manage.manageofusers.mapper;

public interface Convert<T, U> {

    U convert(T t);
}
