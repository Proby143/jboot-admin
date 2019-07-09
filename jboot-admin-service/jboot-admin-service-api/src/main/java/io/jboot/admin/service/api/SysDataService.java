package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.service.entity.model.SysData;
import io.jboot.admin.service.entity.model.SysData;

import java.util.List;
import java.util.Map;

public interface SysDataService  {

    /**
     * 分页查询数据字典信息
     * @param SysData
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<SysData> findPage(SysData SysData, int pageNumber, int pageSize);


    /**
     * 查询字典表 通过类型与code,type获取对应描述
     * @param code
     * @param type
     * @return
     */
    public String getCodeDescByCodeAndType(String code, String type);

    /**
     * 查询字典表 通过类型 与 描述 , 获取code
     * @param type 数据类型
     * @param codeDesc 描述
     * @return
     */
    public String getCodeByCodeDescAndType(String type, String codeDesc);

    /**
     * 查询字典表 通过类型代码 , 获取map
     * @param type
     * @return
     */
    public Map<String, String> getMapByTypeOnUse(String type);

    /**
     * 查询字典表 通过类型代码 、状态, 获取map
     * @param type 数据类型
     * @return
     */
    public Map<String, String> getMapByType(String type);

    /**
     * 根据 type/status 获取数据字典列表
     * @param type 类型编码
     * @return
     */
    public List<SysData> getListByTypeOnUse(String type);

    /**
     * 根据 type 获取全部数据字典列表
     * @param type
     * @return
     */
    public List<SysData> getListByType(String type);

    /**
     * 刷新缓存
     */
    public void refreshCache();

    /**
     * 根据ID查找model
     *
     * @param id
     * @return
     */
    public SysData findById(Object id);


    /**
     * 根据ID删除model
     *
     * @param id
     * @return
     */
    public boolean deleteById(Object id);

    /**
     * 删除
     *
     * @param model
     * @return
     */
    public boolean delete(SysData model);


    /**
     * 保存到数据库
     *
     * @param model
     * @return
     */
    public boolean save(SysData model);

    /**
     * 保存或更新
     *
     * @param model
     * @return
     */
    public boolean saveOrUpdate(SysData model);

    /**
     * 更新 model
     *
     * @param model
     * @return
     */
    public boolean update(SysData model);


    public void join(Page<? extends Model> page, String joinOnField);
    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);
    public void join(Page<? extends Model> page, String joinOnField, String joinName);
    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);


    public void join(List<? extends Model> models, String joinOnField);
    public void join(List<? extends Model> models, String joinOnField, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField, String joinName);
    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);

    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model       要添加到的model
     * @param joinOnField model对于的关联字段
     */
    public void join(Model model, String joinOnField);

    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param attrs
     */
    public void join(Model model, String joinOnField, String[] attrs);


    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param joinName
     */
    public void join(Model model, String joinOnField, String joinName);


    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param joinName
     * @param attrs
     */
    public void join(Model model, String joinOnField, String joinName, String[] attrs);


    public void keep(Model model, String... attrs);

    public void keep(List<? extends Model> models, String... attrs);

}