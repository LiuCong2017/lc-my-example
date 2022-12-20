package demo;

import demo.factory.IDataBase;
import demo.factory.IKeyValueData;

public interface IDataFactory {
    IKeyValueData getKeyValueData();
    IDataBase getDataBase();
}
