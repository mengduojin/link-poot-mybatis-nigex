package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.po.Dto;
import com.po.ItripUser;
import com.po.Itripuserlinkuser;
import com.service.ItripuserLinkuserService;
import com.util.DtoUtil;
import com.util.EmptyUtils;
import com.util.vo.ItripSearchUserLinkUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/userinfo")
public class LinkUserController {
    Jedis jedis=new Jedis("127.0.0.1",6379);
    @Autowired
    private ItripuserLinkuserService itripuserLinkuserService;

    @RequestMapping(value = "/queryuserlinkuser")
    public Dto queryuserlinkuser(HttpServletRequest request,HttpServletResponse response,@RequestBody ItripSearchUserLinkUserVO itripSearchUserLinkUserVO){
        System.out.println("模糊查询方法开始运行");
       String tokenstr=request.getHeader("token");//从浏览器中拿出token的值
        //通过token获取对象的字符串
        //String jsontoken=jedis.get(tokenstr);通过token的key值拿到token的value值
        /*
        把jedis中名字是tokenstr是key的缓存转为json的字符串/
         */
        JSONObject jsonObject=JSONObject.parseObject(jedis.get(tokenstr));
        if(jsonObject==null){
            return DtoUtil.returnFail("token失效","334324");
        }
        System.out.println("token中的token对象"+jsonObject.toJSONString());
        /*把缓存中转换过来的json字符串对象转成对象
        * 用户登录之后才会这个缓存
        * */
        ItripUser scrreitripUser=JSONObject.toJavaObject(jsonObject,ItripUser.class);
        System.out.println("token中对象是"+scrreitripUser.toString());
        System.out.println("拿到itripuser的对象");
        if(tokenstr.equals(jedis.get(scrreitripUser.getUsercode()))) {
            if (EmptyUtils.isNotEmpty(scrreitripUser)) {
                System.out.println("如果登录后拿到浏览器的token");
                //如果从临时属性中拿到是null就赋值null，否则就拿临时属性该拿的值
                Map<String, Object> param = new HashMap<>();
                String linkUserName = itripSearchUserLinkUserVO.getLinkUserName() == null ? null : itripSearchUserLinkUserVO.getLinkUserName();
                //把从临时属性中的linkname存进map集合
                // param.put("linkuseName",linkUserName);
                //把从tokenzhong转换成对象后 从token对象
                //  System.out.println("userId得值"+itripUser.getId());
                // 中拿到userid
                param.put("userId", scrreitripUser.getId());
                System.out.println("userId得值" + scrreitripUser.getId());
                System.out.println("存入map集合");
                System.out.println("存入map集合有几条" + param.size());
                System.out.println("存入map集合的值" + param.get(0));
                try {
                    List<Itripuserlinkuser> itrip = itripuserLinkuserService.getItripuserLinkuserMap(param);
                    System.out.println("list" + itrip.toString());
                    return DtoUtil.returnSuccess("获取联系人成功", itrip);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                return DtoUtil.returnFail("您未登录,请先去登录", "30001");
            }
        }else{
            return DtoUtil.returnFail("token失效", "30002");
        }
return null;
    }
}
