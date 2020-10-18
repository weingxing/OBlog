package com.oxygen.oblog.service.impl;

import com.oxygen.oblog.dao.OptionsMapper;
import com.oxygen.oblog.entity.Options;
import com.oxygen.oblog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 配置信息服务类实现
 * @author Oxygen
 * @since 2020/09/25
 */
@Service
public class OptionsServiceImpl implements OptionsService {
    @Autowired
    private OptionsMapper mapper;

    @Override
    public Map<String, String> getAll() throws Exception {
        List<Options> list = mapper.selectAll();
        Map<String, String> map = new HashMap<>();
        list.forEach( option -> {
            map.put(option.getName(), option.getValue());
                });
        return map;
    }

    @Override
    public String getTitle() throws Exception {
        return mapper.selectByPrimaryKey("title").getValue();
    }

    @Override
    public String getDescription() throws Exception {
        return mapper.selectByPrimaryKey("description").getValue();
    }

    @Override
    public String getKeywords() throws Exception {
        return mapper.selectByPrimaryKey("keywords").getValue();
    }

    @Override
    public String getSiteUrl() throws Exception {
        return mapper.selectByPrimaryKey("site_url").getValue();
    }

    @Override
    public boolean setTitle(String title) throws Exception {
        Options options = new Options();
        options.setName("title");
        options.setValue(title);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public boolean setDescription(String description) throws Exception {
        Options options = new Options();
        options.setName("description");
        options.setValue(description);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public boolean setKeywords(String keywords) throws Exception {
        Options options = new Options();
        options.setName("keywords");
        options.setValue(keywords);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public boolean setSiteUrl(String siteUrl) throws Exception {
        Options options = new Options();
        options.setName("site_url");
        options.setValue(siteUrl);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public String getMusic() throws Exception {
        return mapper.selectByPrimaryKey("music").getValue();
    }

    @Override
    public boolean setMusic(String status) throws Exception {
        Options options = new Options();
        options.setName("music");
        options.setValue(status);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public String getLatex() throws Exception {
        return mapper.selectByPrimaryKey("latex").getValue();
    }

    @Override
    public boolean setLatex(String status) throws Exception {
        Options options = new Options();
        options.setName("latex");
        options.setValue(status);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public String getNotify() throws Exception {
        return mapper.selectByPrimaryKey("notify").getValue();
    }

    @Override
    public boolean setNotify(String status) throws Exception {
        Options options = new Options();
        options.setName("notify");
        options.setValue(status);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public boolean setOption(String name, String value) throws Exception {
        Options options = new Options();
        options.setName(name);
        options.setValue(value);
        return mapper.updateByPrimaryKeySelective(options) > 0;
    }

    @Override
    public String getMusicUrl() throws Exception {
        String mid = mapper.selectByPrimaryKey("music").getValue();
        if("".equals(mid))
            mid = "868773387";
        String api = "https://api.uomg.com/api/rand.music?mid=" + mid + "&format=json";
        URL url = new URL(api);
        URLConnection conn =  url.openConnection();
        conn.setRequestProperty("accept", "*/*" );
        conn.setRequestProperty("Connection", "Keep-Alive" );
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/59.0.3071.86 Safari/537.36");
        conn.connect();

        StringBuilder builder = new StringBuilder();
        //返回浏览器的输出信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream() ));
        String line = reader.readLine();
        line = new String(line.getBytes() , StandardCharsets.UTF_8);

        while(line != null ){
            builder.append(line);
            line = reader.readLine();
        }
        //释放资源
        reader.close();

        return builder.toString();
    }
}
