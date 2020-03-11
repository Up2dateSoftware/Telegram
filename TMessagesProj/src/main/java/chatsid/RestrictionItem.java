package chatsid;

public class RestrictionItem {

    // Company Information
    private String code;
    private String companyName;
    private String dns;

    // Restrictions
    private EntityRestriction group = new EntityRestriction(new PhotoRestriction(false, false), new VideoRestriction(false, false));
    private EntityRestriction personal = new EntityRestriction(new PhotoRestriction(false, false), new VideoRestriction(false, false));
    private Boolean bots = false;
    private Boolean encrypted = false;
    private Boolean block = false;
    private Boolean notcontacts = false;
    private Boolean savePhoto = false;
    private Boolean stickers = false;
    private long timestamp = 0L;
    private int groupCreate = 0;
    private int groupJoin = 0;
    private Boolean multimedia = false;
    private int channelCreate = 0;
    private int channelJoin = 0;
    private Boolean avatars = false;
    private int refreshInterval = 600;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public EntityRestriction getGroup() {
        return group;
    }

    public void setGroup(EntityRestriction group) {
        this.group = group;
    }

    public EntityRestriction getPersonal() {
        return personal;
    }

    public void setPersonal(EntityRestriction personal) {
        this.personal = personal;
    }

    public Boolean getBots() {
        return bots;
    }

    public void setBots(Boolean bots) {
        this.bots = bots;
    }

    public Boolean getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Boolean getNotcontacts() {
        return notcontacts;
    }

    public void setNotcontacts(Boolean notcontacts) {
        this.notcontacts = notcontacts;
    }

    public Boolean getSavePhoto() {
        return savePhoto;
    }

    public void setSavePhoto(Boolean savePhoto) {
        this.savePhoto = savePhoto;
    }

    public Boolean getStickers() {
        return stickers;
    }

    public void setStickers(Boolean stickers) {
        this.stickers = stickers;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public Boolean getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Boolean multimedia) {
        this.multimedia = multimedia;
    }

    public Boolean getAvatars() {
        return avatars;
    }

    public void setAvatars(Boolean avatars) {
        this.avatars = avatars;
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public int getGroupCreate() {
        return groupCreate;
    }

    public void setGroupCreate(int groupCreate) {
        this.groupCreate = groupCreate;
    }

    public int getGroupJoin() {
        return groupJoin;
    }

    public void setGroupJoin(int groupJoin) {
        this.groupJoin = groupJoin;
    }

    public int getChannelCreate() {
        return channelCreate;
    }

    public void setChannelCreate(int channelCreate) {
        this.channelCreate = channelCreate;
    }

    public int getChannelJoin() {
        return channelJoin;
    }

    public void setChannelJoin(int channelJoin) {
        this.channelJoin = channelJoin;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }
}

