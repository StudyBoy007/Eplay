package dao.Impl;

import bean.PlayerBean;
import dao.PlayerDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.C3P0Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by mysteriousTime
 * time on 2019/7/25  10:37
 */
public class playerDaoImpl implements PlayerDao {
    QueryRunner qr;

    public playerDaoImpl() {
        qr = new QueryRunner(C3P0Util.getDs());
    }

    @Override
    public boolean savePlayer(PlayerBean player) {//保存用户信息
        boolean flag = false;
        String sql = "insert into player values (?,?,?,?,?,?,?,?,?) ";
        try {
            int update = qr.update(sql, player.getId(), player.getPlayerID(),
                    player.getGameID(), player.getGameLeve(),
                    player.getPicPath(), player.getOrderNum(), player.getMoney(),
                    player.getIntroduce(), player.getStatus());

            if (update != 0) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println("保存用户失败："+e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean delPlayer(PlayerBean player) {//删除用户
        boolean flag = false;
        String sql="delete from player where id=?";
        try {
            int a=qr.update(sql,player.getId());
            if (a!=0){
                flag=true;
            }
        } catch (SQLException e) {
            System.out.println("删除用户失败："+e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean updatePlayer(PlayerBean player) {//更新用户信息
        boolean flag=false;
        String sql="update player set picPath=?,orderNum=?,money=?,introduce=?,status=? where id=?";
        try {
            int a=qr.update(sql,player.getPicPath(),player.getOrderNum(),player.getMoney(),player.getIntroduce(),player.getStatus(),player.getId());
            if (a!=0){
                System.out.println("更改成功");
                flag=true;
            }
        } catch (SQLException e) {
            System.out.println("修改用户信息失败："+e.getMessage());
        }
        System.out.println(flag);
        return flag;

    }
/**
 * 查看所有用户信息
 */

    @Override
    public List<PlayerBean> selectAllPlayers() {
        PlayerBean player;
        List<PlayerBean> list=new ArrayList<>();
        String sql="SELECT playerID,staffName,player.gameID,`level`,gameName,photoPath,gender,picPath,gameLeve,orderNum,player.money,introduce,player.`status` FROM player,user,gamelist,level WHERE player.playerID=user.id and player.gameID=gamelist.id and `level`.id=player.`level`";
        try {
           player= qr.query(sql,new BeanHandler<>(PlayerBean.class));

            if (player!=null){
                list.add(player);
                System.out.println(list);
                return list;
            }
        } catch (SQLException e) {
            System.out.println("查看所有用户信息失败"+e.getMessage());
        }
        System.out.println("查询所有用户信息失败！");
        return null;
    }
/*
* 分页查询
* */
    @Override
    public List<PlayerBean> pageAllPlayers(int currentPage, int pageSize) {
        PlayerBean player;
        List<PlayerBean> list=new ArrayList<>();
        String sql="SELECT playerID,staffName,player.gameID,`level`,gameName,photoPath,gender,picPath,gameLeve,orderNum,player.money,introduce,player.`status` FROM player,user,gamelist,level WHERE player.playerID=user.id and player.gameID=gamelist.id and `level`.id=player.`level`ORDER BY player.id LIMIT ?, ?";
        try {
          player=qr.query(sql,new BeanHandler<>(PlayerBean.class),(currentPage-1)*pageSize,pageSize);
          if (player!=null){
              list.add(player);
              System.out.println("添加分页成功");
              return list;
          }

        } catch (SQLException e) {
            System.out.println("分页查询"+e.getMessage());
        }

        return null;
    }

    @Override
    public List<PlayerBean> selectPlayersByStatus(int status) {
        PlayerBean player;
        List<PlayerBean> list=new ArrayList<>();
        String sql="SELECT playerID,staffName,player.gameID,`level`,gameName,photoPath,gender,picPath,gameLeve,orderNum,player.money,introduce FROM player,user,gamelist,level WHERE player.playerID=user.id and player.gameID=gamelist.id and `level`.id=player.`level` and player.`status`=?";
        try {
             player=qr.query(sql,new BeanHandler<>(PlayerBean.class),status);
            if (player!=null){
                list.add(player);
                System.out.println("根据状态查询成功");
                return list;
            }
        } catch (SQLException e) {
            System.out.println("根据状态查询失败："+e.getMessage());
        }
        return null;
    }

    @Override
    public List<PlayerBean> pagePlayersByStatus(int status, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<PlayerBean> selectPlayersByPrice(double price) {
        return null;
    }

    @Override
    public List<PlayerBean> pagePlayersByPrice(double price, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<PlayerBean> selectPlayersByGame(String gameName) {
        return null;
    }

    @Override
    public List<PlayerBean> pagePlayersByGame(String gameName, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<PlayerBean> selectPlayersByGender(String gender) {
        return null;
    }

    @Override
    public List<PlayerBean> pagePlayersByGender(String gender, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<PlayerBean> selectPlayersByGameAndLevel(String gameName, String level) {
        return null;
    }

    @Override
    public List<PlayerBean> pagePlayersByGameAndLevel(String gameName, String level, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<PlayerBean> selectPlayersByGameAndLevelAndGender(String gameName, String level, String gender) {
        return null;
    }

    @Override
    public List<PlayerBean> pagePlayersByGameAndLevelAndGender(String gameName, String level, String gender, int currentPage, int pageSize) {
        return null;
    }

    @Override
    public PlayerBean selectPlayByName(String playerName) {
        return null;
    }

    @Override
    public PlayerBean selectPlayById(String playerId) {
        return null;
    }
}
