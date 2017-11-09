package college.com.workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by Neel Patel on 10/8/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String tab_name="person";
    public static final String col_id="person_id";
    public static final String col_name="person_name";
    public static final String col_enroll="person_enroll";
    public static final String col_field="person_field";

    public DataBaseHelper(Context context) {
        super(context, "test_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+tab_name
                +"("+col_id+" number(10) primary key, "
                +col_name+" varchar(50) NOT NULL, "
                +col_enroll+" varchar(50) NOT NULL UNIQUE, "
                +col_field+" varchar(50) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insert(Person p){
        try{
            ContentValues value=new ContentValues();
            value.put(col_id,p.getId());
            value.put(col_name,p.getName());
            value.put(col_enroll,p.getEnroll());
            value.put(col_field,p.getField());
            long x=getWritableDatabase().insert(tab_name,"",value);
            return x;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public long update(Person p){
        try{
            ContentValues value=new ContentValues();
            value.put(col_id,p.getId());
            value.put(col_name,p.getName());
            value.put(col_enroll,p.getEnroll());
            value.put(col_field,p.getField());
            long x=getWritableDatabase().update(tab_name,value,col_id+" = ? ",new String[]{id+""});
            return x;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public long delete(int id){
        try{
            long x=getWritableDatabase().delete(tab_name,col_id+" = ? ",new String[]{id+""});
            return x;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public Person getPerson(int id){
        try{

            Cursor c=getReadableDatabase().rawQuery("select "+col_name+" from "+tab_name
                    +" where "+col_id+" = ?",new String[]{id+""});
            if(c.moveToNext()){
                Person p=new Person();
                p.setId(c.getInt(c.getColumnIndex(col_id)));
                p.setName(c.getString(c.getColumnIndex(col_name)));
                p.setEnroll(c.getString(c.getColumnIndex(col_enroll)));
                p.setField(c.getString(c.getColumnIndex(col_field)));
                return p;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Cursor getAll(){
        try{
            ContentValues value=new ContentValues();
            value.put(col_id,id);
            value.put(col_name,name);
            return getReadableDatabase().rawQuery("select * from "+tab_name,null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
