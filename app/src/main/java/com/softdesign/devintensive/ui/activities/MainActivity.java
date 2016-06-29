package com.softdesign.devintensive.ui.activities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


import com.softdesign.devintensive.R;
import com.softdesign.devintensive.data.managers.DataManager;
import com.softdesign.devintensive.utils.ConstantManager;
import com.softdesign.devintensive.utils.RoundImage;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";


    private List <EditText> mEditTexts;
    private EditText mMobileEditText, mEmailEditText, mVkEditText, mGithubEditText, mAboutEditText;
    private TextView mRatingTextView, mCodeStringsTextView, mProjectsTextView;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFab;


    private boolean mCurrentEditMode = false;


    private DataManager mDataManager;


    /** Метод onCreate() вызывается при создании или перезапуске активности. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRatingTextView = (TextView) findViewById(R.id.rating_count);
        mCodeStringsTextView = (TextView) findViewById(R.id.code_strings_count);
        mProjectsTextView = (TextView) findViewById(R.id.projects_count);


        mEditTexts = new ArrayList<>();
        mMobileEditText = (EditText) findViewById(R.id.mobile);
        mEditTexts.add(mMobileEditText);
        mEmailEditText = (EditText) findViewById(R.id.email);
        mEditTexts.add(mEmailEditText);
        mVkEditText = (EditText) findViewById(R.id.vk);
        mEditTexts.add(mVkEditText);
        mGithubEditText = (EditText) findViewById(R.id.github);
        mEditTexts.add(mGithubEditText);
        mAboutEditText = (EditText) findViewById(R.id.about);
        mEditTexts.add(mAboutEditText);


        mDataManager = new DataManager();
        loadUserData();


        List<String> userData = mDataManager.getPreferencesManager().loadUserData();
        changeTextOnTextView(mEditTexts, userData);


        mRatingTextView.setText("4");
        mCodeStringsTextView.setText("5100");
        mProjectsTextView.setText("3");


        mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);


        setupToolbar();


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);


        setupDrawer();


        mFab = (FloatingActionButton)findViewById(R.id.fab);
        mFab.setOnClickListener(this);


        if (savedInstanceState == null){
            //выполняется, если активность создана впервые
        } else {
            //выполняется, если активность уже создалась
            mCurrentEditMode = savedInstanceState.getBoolean(ConstantManager.EDIT_MODE_TAG, false);
        }


        Log.d(TAG, "Выполнен метод onCreate");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    /** За onCreate() всегда следует вызов onStart(), но перед onStart() не обязательно должен
     * идти onCreate(), onStart() может вызываться и для возобновления работы приостановленного
     * приложения.*/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Выполнен метод onStart");
    }


    /** Метод onResume() вызывается после onStart(). Также может вызываться после onPause(). */
    @Override
    protected void onResume() {
        super.onResume();


        Log.d(TAG, "Выполнен метод onResume");
    }


    /** Метод onPause() вызывается после сворачивания текущей активности или перехода к
     * новому. От onPause() можно перейти к вызову либо onResume(), либо onStop().*/
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Выполнен метод onPause");
    }


    /** Метод onStop() вызывается, когда окно становится невидимым для пользователя. Это может
     * произойти при её уничтожении, или если была запущена другая активность (существующая или
     * новая), перекрывшая окно текущей активности. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Выполнен метод onStop");
    }


    /** Если окно возвращается в приоритетный режим после вызова onStop(), то в этом случае
     * вызывается метод onRestart(). Т.е. вызывается после того, как активность была остановлена и
     * снова была запущена пользователем. Всегда сопровождается вызовом метода onStart(). */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Выполнен метод onRestart");
    }


    /** Метод вызывается по окончании работы активности, при вызове метода finish() или в случае,
     * когда система уничтожает этот экземпляр активности для освобождения ресурсов. */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Выполнен метод onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ConstantManager.EDIT_MODE_TAG, mCurrentEditMode);
    }


    private void setupDrawer(){
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        setupDrawerHeader(navigationView, getRoundBitmap(R.drawable.avatar), "Владимир", "makhmutov.va@mail.ru");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setCheckable(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


    /**
     * Возвращает круглую картинку. Оригинальная картинка берется из ресурсов
     * @param drawableRes ссылка на оригинальную картинку
     * @return
     */
    private Bitmap getRoundBitmap(int drawableRes){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        bitmap = RoundImage.getRoundedBitmap(bitmap);
        return bitmap;
    }


    /**
     * Настраивает шапку NavigationView
     * @param avatar ссылка на картинку
     * @param name имя
     * @param email почта
     */
    private void setupDrawerHeader(NavigationView parent, Bitmap avatar, String name, String email){
        View view = parent.getHeaderView(0);
        if (avatar != null) {
            ImageView imageView = (ImageView)view.findViewById(R.id.user_avatar);
            imageView.setImageBitmap(avatar);
        }
        if (name != null){
            TextView textView = (TextView)view.findViewById(R.id.user_name);
            textView.setText(name);
        }
        if (email != null){
            TextView textView = (TextView)view.findViewById(R.id.user_email);
            textView.setText(email);
        }
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Показывает Snackbar
     * @param message
     */
    private void showSnackBar(String message){
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }


    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                if (mCurrentEditMode){
                    changeEditMode(false);
                    mCurrentEditMode = false;
                } else {
                    changeEditMode(true);
                    mCurrentEditMode = true;
                }
                break;
        }
    }


    /**
     * Переключает режимы редактирования
     * @param mode если false - просмотр, если true - редактирование
     */
    private void changeEditMode(boolean mode){
        if (mode) {
            mFab.setImageResource(R.drawable.ic_check_black_24dp);
            for (EditText editText : mEditTexts) {
                editText.setEnabled(true);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
            }
        } else {
            mFab.setImageResource(R.drawable.ic_mode_edit_black_24dp);
            for (EditText editText : mEditTexts) {
                editText.setEnabled(false);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
                saveUserData();
            }
        }
    }


    /**
     * Меняет текст EditText'a на значение из сохраненных данных
     * @param editTexts коллекция из EditText'ов
     * @param values коллекция строк сохраненных данных
     */
    private void changeTextOnTextView(List<EditText> editTexts, List<String> values){
        for (int i = 0; i < editTexts.size(); i++){
            try{
                if (values.get(i).equals("null")){
                    editTexts.get(i).setText("");
                } else {
                    editTexts.get(i).setText(values.get(i));
                }
            } catch (NullPointerException e){
                editTexts.get(i).setText("");
            }
        }
    }


    /**
     * Загружает пользовательские данные
     */
    private void loadUserData(){
        List<String> userData = mDataManager.getPreferencesManager().loadUserData();
        for (int i = 0; i < userData.size(); i++){
            mEditTexts.get(i).setText(userData.get(i));
        }
    }


    /**
     * Сохраняет пользовательские данные
     */
    private void saveUserData(){
        List<String> userData = new ArrayList<>();
        for (EditText userField : mEditTexts){
            userData.add(userField.getText().toString());
        }
        mDataManager.getPreferencesManager().saveUserData(userData);
    }
}