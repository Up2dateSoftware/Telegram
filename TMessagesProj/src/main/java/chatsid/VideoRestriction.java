package chatsid;

public class VideoRestriction {
    private Boolean send;
    private Boolean receive;

    public VideoRestriction(Boolean send, Boolean receive) {
        this.send = send;
        this.receive = receive;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public Boolean getReceive() {
        return receive;
    }

    public void setReceive(Boolean receive) {
        this.receive = receive;
    }
}
