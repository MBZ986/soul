package cn.smartrick.soul.dto;

import cn.smartrick.soul.constant.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    private Integer code;
    private String msg;
    private Object data;

    public static Msg succ(String op, String word, Object data) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.SUCCESS.getCode());
        Msg.setMsg(op + " " + word + " " + "成功");
        Msg.setData(data);
        return Msg;
    }

    public static Msg succ(String op, String word) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.SUCCESS.getCode());
        Msg.setMsg(op + " " + word + " " + "成功");
        return Msg;
    }

    public static Msg succ(String detail, Object data) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.SUCCESS.getCode());
        Msg.setMsg(detail);
        Msg.setData(data);
        return Msg;
    }

    public static Msg succ(String detail) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.SUCCESS.getCode());
        Msg.setMsg(detail);
        return Msg;
    }

    public static Msg fail(String op, String word) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.FAIL.getCode());
        Msg.setMsg(op + " " + word + " " + "失败");
        return Msg;
    }

    public static Msg fail(String op, String word, Object data) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.FAIL.getCode());
        Msg.setMsg(op + " " + word + " " + "失败");
        Msg.setData(data);
        return Msg;
    }

    public static Msg fail(String detail, Object data) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.FAIL.getCode());
        Msg.setMsg(detail);
        Msg.setData(data);
        return Msg;
    }

    public static Msg fail(String detail) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.FAIL.getCode());
        Msg.setMsg(detail);
        return Msg;
    }

    public static Msg data(Object data) {
        Msg Msg = new Msg();
        Msg.setCode(ResponseStatus.SUCCESS.getCode());
        Msg.setData(data);
        return Msg;
    }

    public static Msg res(boolean flag, String op, String word, Object data) {
        return flag ? succ(op, word, data) : fail(op, word);
    }

    public static Msg res(boolean flag, String op, String word) {
        return flag ? succ(op, word) : fail(op, word);
    }

    public static Msg res(Object data,String op, String word) {
        return data!=null ? succ(op, word, data) : fail(op, word);
    }

    public static Msg res(Object data,String detail) {
        return data!=null ? succ(detail, data) : fail(detail);
    }
}
