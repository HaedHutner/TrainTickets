package io.github.haedhutner.gui;

import io.github.haedhutner.db.DBManager;
import io.github.haedhutner.entity.Entity;

public interface CreateFilterUpdateDialog<T extends Entity<ID>, ID> {

    default void createOk() {
        T entity = getEntity();
        if (entity == null) return;
        getManager().insert(entity);
        dispose();
        ApplicationGUI.getInstance().updateTables();
    }

    default void updateOk() {
        T entity = getEntity();
        if (entity == null) return;
        getManager().update(entity);
        dispose();
        ApplicationGUI.getInstance().updateTables();
    }

    default void filterOk() {
        T entity = getEntity();
        if ( entity == null ) return;
        getManager().filter(entity);
        dispose();
        ApplicationGUI.getInstance().updateTables();
    }

    default void filterCancel() {
        getManager().filter(null);
        ApplicationGUI.getInstance().updateTables();
    }

    DBManager<T, ID> getManager();

    T getEntity();

    void setEntity(T entity);

    void dispose();
}
