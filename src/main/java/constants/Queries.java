package constants;

public class Queries {
    public static final String TABLE = "inf";
    public static final String MAIN_COLUMN = "profileAndWallet";
    public static final String SELECT_ALL = "select * from " + TABLE + " where id = ?";
    public static final String INSERT = "insert into " + TABLE + "(id, " + MAIN_COLUMN +") values(?, ?)";
    public static final String UPDATE = "update " + TABLE + " set " + MAIN_COLUMN + " = ? where id = ?";
}
