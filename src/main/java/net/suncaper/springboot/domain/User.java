package net.suncaper.springboot.domain;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.sho_id
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    private String shoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.name
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.phone
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.email
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    private String email;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.id
     *
     * @return the value of t_user.id
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.id
     *
     * @param id the value for t_user.id
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.sho_id
     *
     * @return the value of t_user.sho_id
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public String getShoId() {
        return shoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.sho_id
     *
     * @param shoId the value for t_user.sho_id
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public void setShoId(String shoId) {
        this.shoId = shoId == null ? null : shoId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.name
     *
     * @return the value of t_user.name
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.name
     *
     * @param name the value for t_user.name
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.phone
     *
     * @return the value of t_user.phone
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.phone
     *
     * @param phone the value for t_user.phone
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.email
     *
     * @return the value of t_user.email
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.email
     *
     * @param email the value for t_user.email
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getShoId() == null ? other.getShoId() == null : this.getShoId().equals(other.getShoId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Tue Jul 30 18:32:21 GMT+08:00 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getShoId() == null) ? 0 : getShoId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }
}