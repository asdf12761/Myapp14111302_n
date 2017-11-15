package com.example.auser.myapp14111302_n;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.auser.myapp14111302_n.data.Student;
import com.example.auser.myapp14111302_n.data.StudentDAOFileImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by yvtc on 2017/11/15.
 */

@RunWith(AndroidJUnit4.class)
public class MyDAOFileTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAOFileImpl dao = new StudentDAOFileImpl(appContext);
        dao.clear();
        dao.add(new Student("AA", "11", "aabb"));
        dao.clear();
        dao.add(new Student("BB", "22", "aabb"));
        dao.add(new Student("CC", "33", "aabb"));
        Student[] stus = dao.getData();
        assertEquals(stus.length, 2);

    }
}
