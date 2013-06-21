package za.co.yellowfire.carat.web;

import za.co.yellowfire.carat.db.ItemDao;

import javax.faces.model.DataModel;

public class ItemDataModel extends DataModel {

    private ItemDao dao;

    @Override
    public boolean isRowAvailable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getRowCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getRowData() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getRowIndex() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRowIndex(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getWrappedData() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setWrappedData(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
