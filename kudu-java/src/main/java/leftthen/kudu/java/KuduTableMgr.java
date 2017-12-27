package leftthen.kudu.java;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.shaded.com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class KuduTableMgr {
    public static void main(String[] args) throws KuduException {
        KuduClient client = KuduClientUtils.getDefaultKuduClient();
        String tableName = "user";

        System.out.println(" table " + tableName + " exists : " + client.tableExists(tableName));
        client.createTable(tableName, getSchemaWithAllTypes(), getBasicCreateTableOptions());

        System.out.println(" table " + tableName + " exists : " + client.tableExists(tableName));
//        client.alterTable()
        client.deleteTable(tableName);
        System.out.println(" table " + tableName + " exists : " + client.tableExists(tableName));
    }

    public static Schema getBasicSchema() {
        ArrayList<ColumnSchema> columns = new ArrayList<ColumnSchema>(5);
        columns.add(new ColumnSchema.ColumnSchemaBuilder("key", Type.INT32).key(true).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column1_i", Type.INT32).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column2_i", Type.INT32).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column3_s", Type.STRING)
                .nullable(true)
                .desiredBlockSize(4096)
                .encoding(ColumnSchema.Encoding.DICT_ENCODING)
                .compressionAlgorithm(ColumnSchema.CompressionAlgorithm.LZ4)
                .build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column4_b", Type.BOOL).build());
        return new Schema(columns);
    }

    public static Schema getSchemaWithAllTypes() {
        List<ColumnSchema> columns =
                ImmutableList.of(
                        new ColumnSchema.ColumnSchemaBuilder("int8", Type.INT8).key(true).build(),
                        new ColumnSchema.ColumnSchemaBuilder("int16", Type.INT16).build(),
                        new ColumnSchema.ColumnSchemaBuilder("int32", Type.INT32).build(),
                        new ColumnSchema.ColumnSchemaBuilder("int64", Type.INT64).build(),
                        new ColumnSchema.ColumnSchemaBuilder("bool", Type.BOOL).build(),
                        new ColumnSchema.ColumnSchemaBuilder("float", Type.FLOAT).build(),
                        new ColumnSchema.ColumnSchemaBuilder("double", Type.DOUBLE).build(),
                        new ColumnSchema.ColumnSchemaBuilder("string", Type.STRING).build(),
                        new ColumnSchema.ColumnSchemaBuilder("binary-array", Type.BINARY).build(),
                        new ColumnSchema.ColumnSchemaBuilder("binary-bytebuffer", Type.BINARY).build(),
                        new ColumnSchema.ColumnSchemaBuilder("null", Type.STRING).nullable(true).build(),
                        new ColumnSchema.ColumnSchemaBuilder("timestamp", Type.UNIXTIME_MICROS).build());

        return new Schema(columns);
    }

    public static CreateTableOptions getBasicCreateTableOptions() {
        return new CreateTableOptions().setRangePartitionColumns(ImmutableList.of("int8")).setNumReplicas(1);
    }
}
