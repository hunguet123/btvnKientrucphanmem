public class Play {
    private String name;
    private String type;
    private String playID;
    public Play() {
    }

    public Play(String playID, String _name, String _type) {
        this.name = _name;
        this.type = _type;
        this.playID = playID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }
}