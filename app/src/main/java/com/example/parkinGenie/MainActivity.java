package com.example.parkinGenie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkinGenie.Entities.CarPark;
import com.example.parkinGenie.utils.Utils;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    public static int cpId;
    Spinner cpList;
    TextView txtAddress, txtPhone, txtWebsite, txtTariff, txtOpeningTimes, txtAvailability, txtCapacity;
    ArrayList<String> cpNames;
    ArrayList<CarPark> carparkList;
    Button btnBook;
    ImageView imgAddress, imgPhone, imgWebsite,imgOpening, imgTariff;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpList = findViewById(R.id.cpSpinner);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtWebsite = findViewById(R.id.txtWebsite);
        txtTariff = findViewById(R.id.txtTariff);
        txtOpeningTimes = findViewById(R.id.txtOpeningTimes);
        txtAvailability = findViewById(R.id.txtAvailability);
        txtCapacity = findViewById(R.id.txtCapacity);
        btnBook = findViewById(R.id.btn_book);
        imgAddress = findViewById(R.id.img_address);
        imgPhone = findViewById(R.id.img_phone);
        imgWebsite = findViewById(R.id.img_website);
        imgOpening = findViewById(R.id.img_opening);
        imgTariff = findViewById(R.id.img_tariff);



        //Access to database
        DatabaseOpenHelper conn = new DatabaseOpenHelper(this, "parkinGenie.db", null, 1);
        db = conn.getWritableDatabase();

        consultCpList();
        obtainList();
        consultTariff();
        consultOpeningTimes();


        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cpNames);
        cpList.setAdapter(adapter);

        //method to select an option from spinner
        cpList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    btnBook.setVisibility(View.VISIBLE);
                    imgAddress.setVisibility(View.VISIBLE);
                    imgPhone.setVisibility(View.VISIBLE);
                    imgWebsite.setVisibility(View.VISIBLE);
                    imgOpening.setVisibility(View.VISIBLE);
                    imgTariff.setVisibility(View.VISIBLE);

                    txtAvailability.setText(carparkList.get(position - 1).getFree_spaces() + " spaces available");
                    txtAddress.setText(carparkList.get(position - 1).getAddress());
                    txtWebsite.setText(carparkList.get(position - 1).getWebsite());
                    txtPhone.setText(carparkList.get(position - 1).getPhone());
                    txtCapacity.setText("Capacity:  "+carparkList.get(position-1).getTot_spaces()+" spaces");
                    cpId = carparkList.get(position-1).getCarParkId();
                    txtTariff.setText(consultTariff());
                    txtOpeningTimes.setText(consultOpeningTimes());

                } else {
                    txtAvailability.setText("");
                    txtAddress.setText("");
                    txtWebsite.setText("");
                    txtPhone.setText("");
                    txtTariff.setText("");
                    txtOpeningTimes.setText("");
                    txtOpeningTimes.setText("");
                    btnBook.setVisibility(View.INVISIBLE);
                    imgAddress.setVisibility(View.INVISIBLE);
                    imgPhone.setVisibility(View.INVISIBLE);
                    imgWebsite.setVisibility(View.INVISIBLE);
                    imgOpening.setVisibility(View.INVISIBLE);
                    imgTariff.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //adding the menu to this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pg_menu, menu);
        return true;
    }

    //onclick listener for items (login button)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(MainActivity.this, LogIn.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(MainActivity.this, AddCarPark.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(MainActivity.this, AboutUs.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //method to get array of objects from table
    private void consultCpList() {

        CarPark carpark;
        carparkList = new ArrayList<CarPark>();

        Cursor cp = db.rawQuery("SELECT * FROM " + Utils.TABLE_CARPARK + " WHERE " + Utils. FREE_SPACES + " > 0 ", null);

        while (cp.moveToNext()) {
            carpark = new CarPark();
            carpark.setCarParkId(cp.getInt(0));
            carpark.setUserId(cp.getInt(1));
            carpark.setName(cp.getString(2));
            carpark.setWebsite(cp.getString(3));
            carpark.setAddress(cp.getString(4));
            carpark.setPhone(cp.getString(5));
            carpark.setGps(cp.getString(6));
            carpark.setTot_spaces(cp.getInt(7));
            carpark.setFree_spaces(cp.getInt(8));
            carpark.setHeight_restrictions(cp.getString(9));
            carpark.setPayment_methods(cp.getString(10));
            carparkList.add(carpark);
        }

    }

    //method to populate spinner
    private void obtainList() {
        cpNames = new ArrayList<String>();
        cpNames.add("(Select Car Park)");

        for (int i = 0; i < carparkList.size(); i++) {
            cpNames.add(carparkList.get(i).getName());
        }
    }


    private String consultTariff() {

            //select tariff info from table tariff where carparkid = id order by orderId
            Cursor tcursor = db.rawQuery("SELECT " + Utils.TARIFF_INFO + " FROM " + Utils.TABLE_TARIFF
                    + " WHERE " + Utils.CARPARKID + "= " +cpId+ " ORDER BY " + Utils.ORDERID, new String[]{});
            StringBuffer buffer = new StringBuffer();

            while (tcursor.moveToNext()) {
                String tariff = tcursor.getString(0);
                buffer.append(tariff + "\n");
            }

            return buffer.toString();
    }


    private String consultOpeningTimes() {

        //select tariff info from table tariff where carparkid = id order by orderId
        Cursor opCursor = db.rawQuery("SELECT " +Utils.DAY_OF_WEEK+ ", " +Utils.OPENING_INFO+" FROM "
                +Utils.TABLE_OPENING_TIMES + " WHERE " + Utils.CARPARKID + "= " +cpId+ " ORDER BY " + Utils.ORDERID, new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (opCursor.moveToNext()) {
            String dayOfWeek = opCursor.getString(0);
            buffer.append(dayOfWeek+"  ");
            String openingInfo = opCursor.getString(1);
            buffer.append(openingInfo+"\n");
        }

        return buffer.toString();
    }

    public void addBooking(View view) {
        close();
        startActivity(new Intent(getApplicationContext(), CreateBooking.class));
    }

    //close the database connection
    public void close(){
        if (db!=null) {
            this.db.close();
        }
    } // close

}


