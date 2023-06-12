package per.study.netty.protocol;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/8
 **/
// 协议包
public class MessageProtocol {
    private int len;// 关键

    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
