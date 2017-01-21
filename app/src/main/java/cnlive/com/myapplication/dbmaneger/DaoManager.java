package cnlive.com.myapplication.dbmaneger;

import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author chenshuo
 * @time 2017/1/18  14:20
 * 1.创建数据库
 * 2.创建数据哭的表
 * 3.对数据库的增删改查
 * 4.数据的升级
 *
 */
public class DaoManager {
    public static final String TAG = DaoManager.class.getSimpleName();
    public static final String DB_NAME = "myDB.sqlitee";
    private volatile static DaoManager manager;//多线程访问对象
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static DaoMaster.DevOpenHelper helper;
    private Context context;

    public static DaoManager getInstance() {
        DaoManager instance = null;
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    manager = instance;
                }
            }
        }
        return instance;
    }

    /**
     * 判断是否有数据库，没有则创建数据库
     */
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;

    }

    /**
     * 完成对数据库的增删改查
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 日志
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public void closeHelper() {
        if(helper!=null){
            helper.close();
            helper=null;
        }


    }

    public void init(Context context){
        this.context=context;
    }
/**
 * 关闭数据库
 * */
    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    public  void  closeConnection(){
        closeHelper();
        closeDaoSession();
    }
}
