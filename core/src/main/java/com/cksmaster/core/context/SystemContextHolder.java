package com.cksmaster.core.context;


import com.cksmaster.core.entity.UserSystemContext;

/**
 * {@link UserSystemContext}的Holder
 * User: jeff
 * Date: 13-7-25
 * Time: 上午10:56
 */
public class SystemContextHolder {

    /**
     * 用户线程变量
     */
    private static ThreadLocal<UserSystemContext> userThread = new ThreadLocal<>();
    /**
     * 管理员线程变量
     */
//    private static ThreadLocal<AdminDTO> adminThread = new ThreadLocal<>();

    /**
     * 设置用户线程变量
     *
     * @param userContext 线程变量
     */
    public static void put(UserSystemContext userContext) {
        userThread.set(userContext);
    }

    /**
     * @return 用户线程变量
     */
    public static UserSystemContext getUserContext() {
        return userThread.get();
    }

    /**
     * 清空用户线程变量
     */
    public static void removeUserContext() {
        userThread.remove();
    }


    /**
     * 设置管理员线程变量
     *
     * @param adminDTO 线程变量
     */
//    public static void put(AdminDTO adminDTO) {
//        adminThread.set(adminDTO);
//    }
//
//    /**
//     * @return 管理员线程变量
//     */
//    public static AdminDTO getAdminContext() {
//        return adminThread.get();
//    }
//
//    /**
//     * 清空管理员线程变量
//     */
//    public static void removeAdminContext() {
//        adminThread.remove();
//    }
}