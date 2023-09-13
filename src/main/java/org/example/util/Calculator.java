package org.example.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;


public class Calculator {

    /**
     * Выполняет вычисление математического выражения, заданного строкой.
     * Результат возвращается в виде строки.
     * @param string выражение
     * @return результат
     */
    public static String evaluate(String string){
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
        String result;
        try {
            result = scriptEngine.eval(string).toString();
        } catch (ScriptException e) {
            JOptionPane.showMessageDialog(null, "Некорректный ввод");
            throw new RuntimeException(e);
        }
        return result;
    }
}
