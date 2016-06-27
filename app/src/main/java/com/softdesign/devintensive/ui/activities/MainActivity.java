package com.softdesign.devintensive.ui.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.softdesign.devintensive.R;
import com.softdesign.devintensive.utils.ConstantManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";

    /*
    * метод вызывается при создании активити (после изменении конфигурации/возврата к текущей
    * активности после ее уничтожения.
    *
    * в данном методе инициализируется/производится:
    * - UI пользовательский интерфейс (статика)
    * - инициализация статических данных активити
    * - связь данных со списками (инициализация алаптеров)
    *
    * Не запускать длительные операции по работе с данными в onCreate()!!!
    *
    * @param savedInstanceState - объект со значениями сохраненными в Bundle - состояние UI
    * */


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTitle("Махмутов Владимир");
            Log.d(TAG, "onCreate");
            }

    /*
        * метод вызывается при старте активити перед моментом того как UI станет доступен
        * пользователю, как правило в данном методе происходит регистрация подписки на события
        * остановка которых была произведена в onStop()
         */
        @Override
        protected void onStart() {
            super.onStart();
            Log.d(TAG, "onStart");
        }
        /*
        * метод вызывается когда активити становится доступна пользователю для взаимодействия
        * в данном методе как правило происходит запуск анимаций/аудио/видео/запуск BroedcastReseiver
        * необходиных для реализации UI логин/запуск выполнения потоков и т.д.
        * метод должен быть максимально лекговестным для максимальной отзывчивости UI
         */
        @Override
        protected void onResume() {
            super.onResume();
            Log.d(TAG, "onResume");
        }
        @Override
        protected void onPause() {
            super.onPause();
            Log.d(TAG, "onPause");
        }
        @Override
        protected void onStop() {
            super.onStop();
            Log.d(TAG, "onStop");
        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy");
        }
        @Override
        protected void onRestart() {
            super.onRestart();
            Log.d(TAG, "onRestart");
        }
        @Override
            public void onClick(View v) {
                switch (v.getId()){

                }
        }
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

        }
}
