package wala.volunteerrack;

/**
 * Created by liuqi on 3/14/2016.
 */
public class Opportunity {


    private String COLUMN_ID = "_id";
    private String COLUMN_OPP_ID = "opportunity_id";
    private String COLUMN_URL= "url";
    private String COLUMN_URI= "uri";

    public Opportunity(){
    }
    public Opportunity(String id, String opid,String url,String uri){
        COLUMN_ID = id;COLUMN_OPP_ID = opid;
        COLUMN_URL = url; COLUMN_URI = uri;
    }
    //todo change this.. too lazy to type right now.
    public String getCOLUMN_ID(){return COLUMN_ID;}
    public String getCOLUMN_OPP_ID(){return COLUMN_OPP_ID;}
    public String getCOLUMN_URL(){return COLUMN_URL;}
    public String getCOLUMN_URI(){return COLUMN_URI;}

    public void setCOLUMN_ID(String temp){ COLUMN_ID= temp;}
    public void setCOLUMN_OPP_ID(String temp){ COLUMN_OPP_ID= temp;}
    public void setCOLUMN_URL(String temp){ COLUMN_URL= temp;}
    public void setCOLUMN_URI(String temp){ COLUMN_URI= temp;}
}
