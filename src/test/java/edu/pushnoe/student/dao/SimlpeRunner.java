package edu.pushnoe.student.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimlpeRunner {


    public static void main(String[] args) {

        SimlpeRunner sr = new SimlpeRunner();

        sr.runTest();

    }

    private void runTest() {

        try {

          Class cl =  Class.forName("edu.pushnoe.student.dao.DictionaryDaoImplTest");

          Constructor cst = cl.getConstructor();
          Object entity = cst.newInstance();

            Method[] methods = cl.getMethods();

            for (Method m : methods){

                Test ann =    m.getAnnotation(Test.class);

                if (ann !=null) {
                    m.invoke(entity);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
