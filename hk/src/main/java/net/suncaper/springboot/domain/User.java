package net.suncaper.springboot.domain;

import java.util.Arrays;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.id
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.sho_id
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String shoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.name
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.phone
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.email
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.file_title
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String fileTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.file_lenth
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private Long fileLenth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.file_type
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private String fileType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.file_content
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    private byte[] fileContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.id
     *
     * @return the value of t_user.id
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.file_title
     *
     * @return the value of t_user.file_title
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public String getFileTitle() {
        return fileTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.file_title
     *
     * @param fileTitle the value for t_user.file_title
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle == null ? null : fileTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.file_lenth
     *
     * @return the value of t_user.file_lenth
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public Long getFileLenth() {
        return fileLenth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.file_lenth
     *
     * @param fileLenth the value for t_user.file_lenth
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public void setFileLenth(Long fileLenth) {
        this.fileLenth = fileLenth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.file_type
     *
     * @return the value of t_user.file_type
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.file_type
     *
     * @param fileType the value for t_user.file_type
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.file_content
     *
     * @return the value of t_user.file_content
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public byte[] getFileContent() {
        return fileContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.file_content
     *
     * @param fileContent the value for t_user.file_content
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
     */
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getFileTitle() == null ? other.getFileTitle() == null : this.getFileTitle().equals(other.getFileTitle()))
            && (this.getFileLenth() == null ? other.getFileLenth() == null : this.getFileLenth().equals(other.getFileLenth()))
            && (this.getFileType() == null ? other.getFileType() == null : this.getFileType().equals(other.getFileType()))
            && (Arrays.equals(this.getFileContent(), other.getFileContent()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Jul 26 14:15:40 CST 2019
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
        result = prime * result + ((getFileTitle() == null) ? 0 : getFileTitle().hashCode());
        result = prime * result + ((getFileLenth() == null) ? 0 : getFileLenth().hashCode());
        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());
        result = prime * result + (Arrays.hashCode(getFileContent()));
        return result;
    }
}