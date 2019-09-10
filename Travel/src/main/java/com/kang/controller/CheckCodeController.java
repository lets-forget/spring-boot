package com.kang.controller;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CheckCodeController {

    int width=120;
    int height=40;
    Random random=new Random();
    public Color[] color= {
            Color.BLACK,Color.RED,Color.CYAN,Color.YELLOW,
            Color.DARK_GRAY,Color.LIGHT_GRAY,Color.ORANGE,
            Color.MAGENTA,Color.darkGray,Color.DARK_GRAY
    };
    public Color getColor(){
        return color[random.nextInt(color.length)];
    }

    @RequestMapping("/sessionCode")
    public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {
        //创建内存中的图片
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics graphics = image.getGraphics();
        //设置颜色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,width,height);

        //画边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,width-1,height-1);
        //画字母
        String values="abcdefghijklmnopqrstuvwxyz1234567890";
        graphics.setFont(new Font("Fa",Font.PLAIN,25));

        StringBuffer sb=new StringBuffer();
        for (int i = 1; i <=4 ; i++) {
            int index = random.nextInt(values.length());
            Character c = values.charAt(index);
            sb.append(c);
            //将文字画到图片上
            graphics.drawString(c.toString(),i*20,25);
        }
        //存入session域中
        session.setAttribute("session_code",sb.toString());
        
        //画干扰线
        for (int i = 1; i <=10 ; i++) {
            graphics.setColor(getColor());
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }

        ImageIO.write(image,"JPG",response.getOutputStream());
    }
}
