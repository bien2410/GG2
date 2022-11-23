/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom2.gg2;

/**
 *
 * @author ADMIN
 */
import java.util.*;
public class Mission {
    public boolean doing = false;
    public String defaultMission = "Đi tìm ông Ôp";
    public String taskMission[] = new String[20];
    public Queue<String> conservation = new LinkedList<>();
    public int indexMission = -1;
    public boolean checkMission[] = new boolean[20];
    public String currentMission = defaultMission;
    GamePanel gp;

    public Mission(GamePanel gp) {
        this.gp = gp;
        setMission();
    }
    
    public void setMission(){
        int i = 0;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Giải tích";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Đại số";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Tiếng Anh";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Triết";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Thể chất";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 C";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 C++";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Toán rời rạc";
        i++;
        taskMission[i] = "Tiêu diệt " + gp.countM[i] + "/10 Cơ sở dữ liệu";
        i++;
        
    }
    
    public void updateMission(int i){
        switch (i) {
            case 0:
                taskMission[i] = "Tiêu diệt " + gp.countM[0] + "/10 Giải tích";
                break;
            case 1:
                taskMission[i] = "Tiêu diệt " + gp.countM[1] + "/10 Đại số";
                break;
            case 2:
                taskMission[i] = "Tiêu diệt " + gp.countM[5] + "/10 Tiếng Anh";
                break;
            case 3:
                taskMission[i] = "Tiêu diệt " + gp.countM[2] + "/10 Triết";
                break;
            case 4:
                taskMission[i] = "Tiêu diệt " + gp.countM[3] + "/10 Thể chất";
                break;
            case 5:
                taskMission[i] = "Tiêu diệt " + gp.countM[7] + "/10 C";
                break;
            case 6:
                taskMission[i] = "Tiêu diệt " + gp.countM[4] + "/10 C++";
                break;
            case 7:
                taskMission[i] = "Tiêu diệt " + gp.countM[6] + "/10 Toán rời rạc";
                break;
            case 8:
                taskMission[i] = "Tiêu diệt " + gp.countM[8] + "/10 Cơ sở dữ liệu";
                break;
            default:
                return;
        }
        if(currentMission != defaultMission) currentMission = taskMission[indexMission];
    }
    
    public void setConversation(int i){
        conservation.clear();
        switch (i) {
            case 0:
                conservation.add("Tiêu diệt 10 Giải tích\nNgươi có thể tìm chúng khi đi qua cổng ở bên phải.\nNhưng muốn đi qua cổng ngươi cần tìm chìa khóa");
                conservation.add("Đến chỗ chiếc rương đằng kia và ấn ENTER để lấy nó.\nBây giờ thì bắt đầu công việc của người đi!");
                break;
            case 1:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi.");
                conservation.add("Ngươi có thể dùng tiền để hồi sinh lập tức\nhoặc mua bình máu ở Máy bán máu tự động đằng kia\nĐến ấn ENTER để mua và ấn E để sử dụng");
                conservation.add("Mỗi bình máu có giá 50 xu\nVà máy không bán chịu đâu nhé!\nGiờ thì tiếp tục công việc nào!");
                conservation.add("Tiêu diệt 10 Đại số");
                break;
            case 2:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nTa tặng cho ngươi 1 thứ vũ khí mới lợi hại hơn nhiều\nHãy sử dụng nó để tiếp tục công việc.");
                conservation.add("Tiêu diệt 10 Tiếng Anh");
                break;
            case 3:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nChiếc chìa khóa này sẽ giúp ngươi mở ra vùng đấy mới");
                conservation.add("Tiêu diệt 10 Triết");
                break;
            case 4:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nĐể giúp ngươi di chuyển dễ dàng ta đã sửa code\ngiúp ngươi đi nhanh hơn.");
                conservation.add("Tiêu diệt 10 Thể chất");
                break;
            case 5:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nCó vẻ ngươi không sử dụng ám khí nhiều nhỉ\nTa lại sửa code để cho ngươi thêm năng lượng đấy.");
                conservation.add("Tiêu diệt 10 C");
                break;
            case 6:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nChiếc chìa khóa này sẽ dẫn ngươi đến vùng đất nguy hiểm\nHãy cẩn thận!");
                conservation.add("Tiêu diệt 10 C++");
                break;
            case 7:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nChiếc khiên này sẽ giúp ngươi tàng hình\nNhưng ngươi sẽ không thể tấn công.");
                conservation.add("Tiêu diệt 10 Toán rời rạc");
                break;
            case 8:
                conservation.add("Làm tốt lắm đây là phần thưởng của ngươi\nCó vẻ ngươi cần thêm nhiều máu\nĐể ta giúp ngươi nốt lần này");
                conservation.add("Tiêu diệt 10 Cơ sở dữ liệu");
                break;
            default:
                gp.ui.currentDialogue = "Ông Ốp biến mất 1 cách lạ thường.\nĐến nơi tận cùng thế giới để tìm thử xem!";
        }
    }
   
    public void nextMission(){
        gp.setMonsterCounter();
        if(doing) {
            currentMission = defaultMission;
            doing = false;
            gp.npc[0][0].setDialogue(indexMission + 1);
        }
        else{
            doing = true;
            indexMission++;
            currentMission = taskMission[indexMission];
        }
    }
    
    public void checkMission(){
        boolean b = false;
        switch (indexMission) {
            case -1:
                b = true;
                break;
            case 0:
                if(gp.countM[0] == 1) {
                    b = true;
                    checkMission[0] = true;
                }
                break;
            case 1:
                if(gp.countM[1] == 1){
                    b = true;
                    checkMission[1] = true;
                }
                break;
            case 2:
                if(gp.countM[5] == 1){
                    b = true;
                    checkMission[2] = true;
                }
                break;
            case 3:
                if(gp.countM[2] == 1){
                    b = true;
                    checkMission[3] = true;
                }
                break;
            case 4:
                if(gp.countM[3] == 1){
                    b = true;
                    checkMission[4] = true;
                }
                break;
            case 5:
                if(gp.countM[7] == 1){
                    b = true;
                    checkMission[5] = true;
                }
                break;
            case 6:
                if(gp.countM[4] == 1){
                    b = true;
                    checkMission[6] = true;
                }
                break;
            case 7:
                if(gp.countM[6] == 1){
                    b = true;
                    checkMission[7] = true;
                }
                break;
            case 8:
                if(gp.countM[8] == 1){
                    b = true;
                    checkMission[8] = true;
                }
                break;
            default:
                b = false;
                break;
        }
        if(b){
            nextMission();
        }
    }
}
