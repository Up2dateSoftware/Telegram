package chatsid;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import org.telegram.messenger.ApplicationLoader;

import java.util.ArrayList;

public class Restrictions {

    private ArrayList<RestrictionItem> predefinedRestrictions = new ArrayList<>();
    private SharedPreferences sharedPref;

    private static final String sharedPreferencesFileKey = "ChatSIDSharedPreferences";

    private enum SharedPreferencesKeys {
        CompanyName,
        CompanyCode,
        Dns,
        Bots,
        Encrypted,
        Block,
        NotContacts,
        SavePhoto,
        Stickers,
        Timestamp,
        GroupCreate,
        GroupJoin,
        ChannelCreate,
        ChannelJoin,
        Multimedia,
        Avatars,
        RefreshInterval,
        GroupVideoSend,
        GroupVideoReceive,
        GroupPhotoSend,
        GroupPhotoReceive,
        PersonalVideoSend,
        PersonalVideoReceive,
        PersonalPhotoSend,
        PersonalPhotoReceive,
        HasRestrictionSet
    }

    private static Restrictions instance;

    public static Restrictions getInstance() {
        if (instance == null) {
            instance = new Restrictions();
        }
        return instance;
    }

    public Restrictions() {
        sharedPref = ApplicationLoader.applicationContext.getSharedPreferences(sharedPreferencesFileKey, Context.MODE_PRIVATE);
        predefinedRestrictions.add(getBHPhotoRestrictions());
        predefinedRestrictions.add(getTagRestrictions());
        predefinedRestrictions.add(getGeder());
    }

    public boolean setRestriction(String companyCode) {
        for (RestrictionItem restrictionItem : predefinedRestrictions) {
            if (restrictionItem.getCode().equalsIgnoreCase(companyCode)) {
                return updateRestriction(restrictionItem);
            }
        }
        return false;
    }

    public boolean updateRestriction(RestrictionItem restrictionItem) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SharedPreferencesKeys.CompanyCode.name(), restrictionItem.getCode());
        editor.putString(SharedPreferencesKeys.CompanyName.name(), restrictionItem.getCompanyName());
        editor.putString(SharedPreferencesKeys.Dns.name(), restrictionItem.getDns());

        editor.putBoolean(SharedPreferencesKeys.HasRestrictionSet.name(), true);
        editor.putBoolean(SharedPreferencesKeys.Bots.name(), restrictionItem.getBots());
        editor.putBoolean(SharedPreferencesKeys.Encrypted.name(), restrictionItem.getEncrypted());
        editor.putBoolean(SharedPreferencesKeys.Block.name(), restrictionItem.getBlock());
        editor.putBoolean(SharedPreferencesKeys.NotContacts.name(), restrictionItem.getNotcontacts());
        editor.putBoolean(SharedPreferencesKeys.SavePhoto.name(), restrictionItem.getSavePhoto());
        editor.putBoolean(SharedPreferencesKeys.Stickers.name(), restrictionItem.getStickers());
        editor.putBoolean(SharedPreferencesKeys.Multimedia.name(), restrictionItem.getMultimedia());
        editor.putBoolean(SharedPreferencesKeys.Avatars.name(), restrictionItem.getAvatars());

        editor.putLong(SharedPreferencesKeys.Timestamp.name(), restrictionItem.getTimestamp());

        editor.putInt(SharedPreferencesKeys.GroupCreate.name(), restrictionItem.getGroupCreate());
        editor.putInt(SharedPreferencesKeys.GroupJoin.name(), restrictionItem.getGroupJoin());
        editor.putInt(SharedPreferencesKeys.ChannelJoin.name(), restrictionItem.getChannelJoin());
        editor.putInt(SharedPreferencesKeys.ChannelCreate.name(), restrictionItem.getChannelCreate());

        editor.putBoolean(SharedPreferencesKeys.PersonalPhotoReceive.name(), restrictionItem.getPersonal().getPhotoRestriction().getReceive());
        editor.putBoolean(SharedPreferencesKeys.PersonalPhotoSend.name(), restrictionItem.getPersonal().getPhotoRestriction().getSend());
        editor.putBoolean(SharedPreferencesKeys.PersonalVideoReceive.name(), restrictionItem.getPersonal().getVideoRestriction().getReceive());
        editor.putBoolean(SharedPreferencesKeys.PersonalVideoSend.name(), restrictionItem.getPersonal().getVideoRestriction().getSend());

        editor.putBoolean(SharedPreferencesKeys.GroupVideoReceive.name(), restrictionItem.getGroup().getPhotoRestriction().getReceive());
        editor.putBoolean(SharedPreferencesKeys.GroupPhotoSend.name(), restrictionItem.getGroup().getPhotoRestriction().getSend());
        editor.putBoolean(SharedPreferencesKeys.GroupVideoReceive.name(), restrictionItem.getGroup().getVideoRestriction().getReceive());
        editor.putBoolean(SharedPreferencesKeys.GroupVideoSend.name(), restrictionItem.getGroup().getVideoRestriction().getSend());

        return editor.commit();
    }

    @Nullable
    public RestrictionItem getRestrictionItem() {

        if (sharedPref.getBoolean(SharedPreferencesKeys.HasRestrictionSet.name(), false)) {
            RestrictionItem restrictionItem = new RestrictionItem();

            restrictionItem.setCode(sharedPref.getString(SharedPreferencesKeys.CompanyCode.name(), null));
            restrictionItem.setCompanyName(sharedPref.getString(SharedPreferencesKeys.CompanyName.name(), null));
            restrictionItem.setDns(sharedPref.getString(SharedPreferencesKeys.Dns.name(), null));
            restrictionItem.setBots(sharedPref.getBoolean(SharedPreferencesKeys.Bots.name(), false));
            restrictionItem.setEncrypted(sharedPref.getBoolean(SharedPreferencesKeys.Encrypted.name(), false));
            restrictionItem.setBlock(sharedPref.getBoolean(SharedPreferencesKeys.Block.name(), false));
            restrictionItem.setNotcontacts(sharedPref.getBoolean(SharedPreferencesKeys.NotContacts.name(), false));
            restrictionItem.setSavePhoto(sharedPref.getBoolean(SharedPreferencesKeys.SavePhoto.name(), false));
            restrictionItem.setStickers(sharedPref.getBoolean(SharedPreferencesKeys.Stickers.name(), false));
            restrictionItem.setMultimedia(sharedPref.getBoolean(SharedPreferencesKeys.Multimedia.name(), false));
            restrictionItem.setAvatars(sharedPref.getBoolean(SharedPreferencesKeys.Avatars.name(), false));

            restrictionItem.setTimestamp(sharedPref.getLong(SharedPreferencesKeys.Timestamp.name(), 0L));

            restrictionItem.setGroupCreate(sharedPref.getInt(SharedPreferencesKeys.GroupCreate.name(), 0));
            restrictionItem.setGroupJoin(sharedPref.getInt(SharedPreferencesKeys.GroupJoin.name(), 0));
            restrictionItem.setChannelCreate(sharedPref.getInt(SharedPreferencesKeys.ChannelCreate.name(), 0));
            restrictionItem.setChannelJoin(sharedPref.getInt(SharedPreferencesKeys.ChannelJoin.name(), 0));
            restrictionItem.setRefreshInterval(sharedPref.getInt(SharedPreferencesKeys.RefreshInterval.name(), 600));

            restrictionItem.setGroup(new EntityRestriction(new PhotoRestriction(sharedPref.getBoolean(SharedPreferencesKeys.GroupPhotoSend.name(), false), sharedPref.getBoolean(SharedPreferencesKeys.GroupPhotoReceive.name(), false)), new VideoRestriction(sharedPref.getBoolean(SharedPreferencesKeys.GroupVideoSend.name(), false), sharedPref.getBoolean(SharedPreferencesKeys.GroupVideoReceive.name(), false))));
            restrictionItem.setPersonal(new EntityRestriction(new PhotoRestriction(sharedPref.getBoolean(SharedPreferencesKeys.PersonalPhotoSend.name(), false), sharedPref.getBoolean(SharedPreferencesKeys.PersonalPhotoReceive.name(), false)), new VideoRestriction(sharedPref.getBoolean(SharedPreferencesKeys.PersonalVideoSend.name(), false), sharedPref.getBoolean(SharedPreferencesKeys.PersonalVideoReceive.name(), false))));
            return restrictionItem;
        }
        return null;
    }

    /**
     * B&H Photo Restriction Item
     * @return RestrictionItem
     */
    private RestrictionItem getBHPhotoRestrictions() {
        RestrictionItem restrictionItem = new RestrictionItem();

        restrictionItem.setCode("bhphoto");
        restrictionItem.setCompanyName("bhphoto");
        restrictionItem.setDns("http://chatsid.bhphoto.com:5001/livigent/api/chatsid");

        return restrictionItem;
    }

    /**
     * TAG Restriction Item
     * @return RestrictionItem
     */
    private RestrictionItem getTagRestrictions() {
        RestrictionItem restrictionItem = new RestrictionItem();

        restrictionItem.setCode("tag");
        restrictionItem.setCompanyName("tag");
        restrictionItem.setDns("http://tagchatsid.rndsoftwaregroup.com:5001/livigent/api/chatsid");

        return restrictionItem;
    }

    /**
     * Geder Restriction Item
     * @return RestrictionItem
     */
    private RestrictionItem getGeder() {
        RestrictionItem restrictionItem = new RestrictionItem();

        restrictionItem.setCode("9000");
        restrictionItem.setCompanyName("9000");
        restrictionItem.setDns("http://chatsid.geder.org:5001/livigent/api/chatsid");
        restrictionItem.setPersonal(new EntityRestriction(new PhotoRestriction(true, true), new VideoRestriction(true, true)));
        restrictionItem.setGroup(new EntityRestriction(new PhotoRestriction(true, true), new VideoRestriction(false, false)));
        restrictionItem.setBlock(true);
        restrictionItem.setSavePhoto(true);
        restrictionItem.setStickers(true);
        restrictionItem.setGroupCreate(10);
        restrictionItem.setGroupJoin(10);


        return restrictionItem;
    }

}