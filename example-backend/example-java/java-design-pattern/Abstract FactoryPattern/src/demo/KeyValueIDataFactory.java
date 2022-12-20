package demo;

import demo.factory.IDataBase;
import demo.factory.IKeyValueData;
import demo.factory.ikvdimpl.DataStore;
import demo.factory.ikvdimpl.MMKV;
import demo.factory.ikvdimpl.SharedPreferences;

public class KeyValueIDataFactory implements IDataFactory{
    private boolean isMMKV;
    private boolean isDataStore;
    @Override
    public IKeyValueData getKeyValueData() {
        if (isMMKV) {
            return new MMKV();
        }
        if (isDataStore) {
            return new DataStore();
        }
        return new SharedPreferences();
    }


    @Override
    public IDataBase getDataBase() {
        return null;
    }
}
