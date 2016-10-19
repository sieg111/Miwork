/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter = new CategoryAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        findViewById(R.id.numbers).setOnClickListener(this);
//        findViewById(R.id.colors).setOnClickListener(this);
//        findViewById(R.id.phrases).setOnClickListener(this);
//        findViewById(R.id.family).setOnClickListener(this);
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.numbers:
//                startActivity(new Intent(this,NumbersActivity.class));
//                break;
//            case R.id.colors:
//                startActivity(new Intent(this,ColorsActivity.class));
//                break;
//            case R.id.family:
//                startActivity(new Intent(this,FamilyActivity.class));
//                break;
//            case R.id.phrases:
//                startActivity(new Intent(this,PhrasesActivity.class));
//                break;
//        }
//    }

}
