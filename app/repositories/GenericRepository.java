package repositories;

import io.ebean.Ebean;
import io.ebean.Finder;

import java.util.List;

public class GenericRepository<T> {

    protected Finder<Integer, T> finder;
    protected Class<T> typeParameterClass;

    public GenericRepository(Class<T> typeParameterClass){
        this.typeParameterClass = typeParameterClass;
        this.finder = new Finder<>(typeParameterClass);
    }

    public T findByID(Integer id){
        return finder.byId(id);
    }

    public List<T> getAll(){
        return Ebean.find(typeParameterClass)
                .findList();
    }

    public List<T> getAll(String propertyName, String value){
        return Ebean.find(typeParameterClass)
                .where()
                .eq(propertyName, value)
                .findList();
    }

    public List<T> getAll(String orderByClause){
        return Ebean.find(typeParameterClass)
                .orderBy(orderByClause)
                .findList();
    }

    public List<T> getAll(String propertyName, String value, String orderByClause){
        return Ebean.find(typeParameterClass)
                .where()
                .eq(propertyName, value)
                .orderBy(orderByClause)
                .findList();
    }

    public void add(T object){
        Ebean.save(object);
    }

    public void delete(T object){
        Ebean.delete(object);
    }

}
