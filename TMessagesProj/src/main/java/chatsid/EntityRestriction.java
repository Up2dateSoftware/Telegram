package chatsid;

public class EntityRestriction {
    private PhotoRestriction photoRestriction;
    private VideoRestriction videoRestriction;

    public EntityRestriction(PhotoRestriction photoRestriction, VideoRestriction videoRestriction) {
        this.photoRestriction = photoRestriction;
        this.videoRestriction = videoRestriction;
    }

    public PhotoRestriction getPhotoRestriction() {
        return photoRestriction;
    }

    public void setPhotoRestriction(PhotoRestriction photoRestriction) {
        this.photoRestriction = photoRestriction;
    }

    public VideoRestriction getVideoRestriction() {
        return videoRestriction;
    }

    public void setVideoRestriction(VideoRestriction videoRestriction) {
        this.videoRestriction = videoRestriction;
    }



}
