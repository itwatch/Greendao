package cnlive.com.myapplication.dbmaneger;

import android.content.Context;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author chenshuo
 * @time 2017/1/18  15:06
 * 完成对某一张表的操作。在这里擦做的是student
 */
public class CommentUtils<T> {
    private DaoManager manager;
    public CommentUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);

    }
    /**
     * 完成数据库的插入工作
     */
    public boolean inserStudent(T t) {
        boolean flag = false;
        flag = manager.getDaoSession().insert(t) != -1 ? true : false;
        return flag;
    }

    /**
     * 添加集合
     * */
    public boolean insertMuiltStudent(final List<T> t) {
        boolean flag = false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t1 : t) {
                        manager.getDaoSession().insertOrReplace(t1);
                    }
                }
            });
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新数据
     */
    public boolean updateStudent(T t) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(t);
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * 删除数据
     */
    public boolean deletaStudent(T t) {
        boolean flag = false;
        try {
            manager.getDaoSession().delete(t);
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }


    /**
     * 删除所有数据
     * */
    public  boolean deletaAll(Class<T> service){
        boolean flag = false;
        try {
            manager.getDaoSession().deleteAll(service);
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 返回多条记录
     */
    public List<T> listall(Class<T> service) {
        return manager.getDaoSession().loadAll(service);
    }

    /**
     * 返回单条记录
     */
    public T listOneStudent(Class<T> service,long key) {
        return manager.getDaoSession().load(service, key);
    }

    /**
     *Qureybuilder   精确查询；
     *
     * */
    public QueryBuilder<T> queryBuilder(Class<T> service){

        QueryBuilder<T> queryBuilder = manager.getDaoSession().queryBuilder(service);
        //queryBuilder.where(StudentDao.Properties.Age.ge(23)).where(StudentDao.Properties.Address.like("江西"));
        return  queryBuilder;
    }
}
