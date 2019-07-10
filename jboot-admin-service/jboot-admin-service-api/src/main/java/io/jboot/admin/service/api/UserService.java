package io.jboot.admin.service.api;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.admin.service.entity.model.User;
import io.jboot.db.model.Columns;

import java.util.List;

public interface UserService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public User findById(Object id);


    /**
     * find all model
     *
     * @return all <User
     */
    public List<User> findAll();


    /**
     * delete model by primary key
     *
     * @param id
     * @return success
     */
    public boolean deleteById(Object id);


    /**
     * delete model
     *
     * @param model
     * @return
     */
    public boolean delete(User model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(User model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(User model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(User model);


    /**
     * 分页
     *
     * @param page
     * @param pageSize
     * @return
     */
    public Page<? extends Model> paginate(int page, int pageSize);


    public void join(Page<? extends Model> page, String joinOnField);

    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);

    public void join(Page<? extends Model> page, String joinOnField, String joinName);

    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);

    public void join(List<? extends Model> models, String joinOnField);

    public void join(List<? extends Model> models, String joinOnField, String[] attrs);

    public void join(List<? extends Model> models, String joinOnField, String joinName);

    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);

    public void join(Model model, String joinOnField);

    public void join(Model model, String joinOnField, String[] attrs);

    public void join(Model model, String joinOnField, String joinName);

    public void join(Model model, String joinOnField, String joinName, String[] attrs);

    public void keep(Model model, String... attrs);

    public void keep(List<? extends Model> models, String... attrs);

    boolean updateUserInfo(String userId, String name, String age, String sex, String occupation);

    boolean updateDesc(String userId, String desc);

    boolean updateCity(String userId, String city);

    String updateLogin(String relationId, String city, String code);

    Record selectUser(String userId);

    List<Record> selectFootprint(String city, String userId, String sex);

    boolean reportUser(String userId, String reporter, String commentId);

    List<Record> selectChatRecivers(List<String> stringList, String userId);

    String selectUserState(String userId);

    public Page<User> findPage(User user, int pageNumber, int pageSize);
}