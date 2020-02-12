package wasm.data_type;

import network.platon.autotest.junit.annotations.DataSource;
import network.platon.autotest.junit.enums.DataSourceType;
import network.platon.contracts.wasm.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Bytes;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import wasm.beforetest.WASMContractPrepareTest;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author zjsunzone
 *
 * This class is used to test date type of integer.
 */
public class IntegerDataTypeContractTest extends WASMContractPrepareTest {

    @Before
    public void before(){
        prepare();
    }

    @Test
    @DataSource(type = DataSourceType.EXCEL, file = "test.xls", sheetName = "Sheet1",
            author = "zjsunzone", showName = "wasm.base_data_type_01",sourcePrefix = "wasm")
    public void testBaseTypeContract_01() {

        try {
            // deploy contract.
            IntegerDataTypeContract_1 contract = IntegerDataTypeContract_1.deploy(web3j, transactionManager, provider).send();
            String contractAddress = contract.getContractAddress();
            String transactionHash = contract.getTransactionReceipt().get().getTransactionHash();
            collector.logStepPass("IntegerDataTypeContract_01 issued successfully.contractAddress:" + contractAddress + ", hash:" + transactionHash);


        } catch (Exception e) {
            if(e instanceof ArrayIndexOutOfBoundsException){
                collector.logStepPass("IntegerDataTypeContract_01 and could not call contract function");
            }else{
                collector.logStepFail("IntegerDataTypeContract_01 failure,exception msg:" , e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    @DataSource(type = DataSourceType.EXCEL, file = "test.xls", sheetName = "Sheet1",
            author = "zjsunzone", showName = "wasm.base_data_type_02",sourcePrefix = "wasm")
    public void testBaseTypeContract_02() {

        try {
            // deploy contract.
            IntegerDataTypeContract_2 contract = IntegerDataTypeContract_2.deploy(web3j, transactionManager, provider).send();
            String contractAddress = contract.getContractAddress();
            String transactionHash = contract.getTransactionReceipt().get().getTransactionHash();
            collector.logStepPass("IntegerDataTypeContract_01 issued successfully.contractAddress:" + contractAddress + ", hash:" + transactionHash);


        } catch (Exception e) {
            if(e instanceof ArrayIndexOutOfBoundsException){
                collector.logStepPass("IntegerDataTypeContract_02 and could not call contract function");
            }else{
                collector.logStepFail("IntegerDataTypeContract_02 failure,exception msg:" , e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    @DataSource(type = DataSourceType.EXCEL, file = "test.xls", sheetName = "Sheet1",
            author = "zjsunzone", showName = "wasm.base_data_type_03",sourcePrefix = "wasm")
    public void testBaseTypeContract_03() {

        try {
            // deploy contract.
            IntegerDataTypeContract_3 contract = IntegerDataTypeContract_3.deploy(web3j, transactionManager, provider).send();
            String contractAddress = contract.getContractAddress();
            String transactionHash = contract.getTransactionReceipt().get().getTransactionHash();
            collector.logStepPass("IntegerDataTypeContract_3 issued successfully.contractAddress:" + contractAddress + ", hash:" + transactionHash);

            // test: store string
            TransactionReceipt strTr = contract.setString("setString").send();
            String getString = contract.getString().send();
            collector.logStepPass("To invoke setString and getString, getString: " + getString);
            collector.assertEqual(getString, "setString");

            // test: store bool
            TransactionReceipt boolTr = contract.setBool(true).send();
            boolean getBool = contract.getBool().send();
            collector.logStepPass("To invoke setBool and getBool, bool: " + getBool);
            collector.assertEqual(getBool, true);

            // test: store char
            Byte expectByte = (byte)1;
            TransactionReceipt charTr = contract.setChar(expectByte).send();
            collector.logStepPass("To invoke setChar success, txHash: " + charTr.getTransactionHash());
            Byte getChar = contract.getChar().send();
            collector.logStepPass("To invoke getChar success, getChar: " + getChar.byteValue());
            collector.assertEqual(getChar.byteValue(), expectByte.byteValue());

        } catch (Exception e) {
            if(e instanceof ArrayIndexOutOfBoundsException){
                collector.logStepPass("IntegerDataTypeContract_3 and could not call contract function");
            }else{
                collector.logStepFail("IntegerDataTypeContract_3 failure,exception msg:" , e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    @DataSource(type = DataSourceType.EXCEL, file = "test.xls", sheetName = "Sheet1",
            author = "zjsunzone", showName = "wasm.base_data_type_04",sourcePrefix = "wasm")
    public void testBaseTypeContract_04() {

        try {
            // deploy contract.
            IntegerDataTypeContract_4 contract = IntegerDataTypeContract_4.deploy(web3j, transactionManager, provider).send();
            String contractAddress = contract.getContractAddress();
            String transactionHash = contract.getTransactionReceipt().get().getTransactionHash();
            collector.logStepPass("IntegerDataTypeContract_4 issued successfully.contractAddress:" + contractAddress + ", hash:" + transactionHash);

            // test: store address
            Address expectAddr = new Address("0x5b05e7a3e2a688c5e5cc491545a84a1efc66c1b1");
            TransactionReceipt addrTr = contract.setAddress(expectAddr.getValue()).send();
            collector.logStepPass("To invoke setAddress success, txHash: " + addrTr.getTransactionHash());
            String getAddress = contract.getAddress().send();
            collector.logStepPass("To invoke getAddress success, getAddress: " + getAddress);
            //collector.assertEqual(getAddress, expectAddr);

            // test: store u256
            String expectU256 = "100000";
            TransactionReceipt u256TR = contract.setU256(Long.valueOf(expectU256)).send();
            collector.logStepPass("To invoke setU256 success, txHash: " + u256TR.getTransactionHash());
            String getU256 = contract.getU256().send();
            collector.logStepPass("To invoke getU256 success, getU256: " + getU256);
            collector.assertEqual(getU256, expectU256);

            // test: store h256
            String expectH256 = "0x80b543239ae8e4f679019719312524d10f14fef79fd0d9117d810bffdedf608e";
            TransactionReceipt h256Tr = contract.setH256(expectH256).send();
            collector.logStepPass("To invoke setH256 success, txHash: " + h256Tr.getTransactionHash());
            String getH256 = contract.getH256().send();
            collector.logStepPass("To invoke getH256 success, getH256: " + getH256);
            //collector.assertEqual(getH256, expectH256);

        } catch (Exception e) {
            if(e instanceof ArrayIndexOutOfBoundsException){
                collector.logStepPass("IntegerDataTypeContract_4 and could not call contract function");
            }else{
                collector.logStepFail("IntegerDataTypeContract_4 failure,exception msg:" , e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    @DataSource(type = DataSourceType.EXCEL, file = "test.xls", sheetName = "Sheet1",
            author = "zjsunzone", showName = "wasm.integer_data_type",sourcePrefix = "wasm")
    public void testIntegerTypeContract() {

        try {
            // deploy contract.
            /*IntegerDataTypeContract contract = IntegerDataTypeContract.deploy(web3j, transactionManager, provider).send();
            String contractAddress = contract.getContractAddress();
            String transactionHash = contract.getTransactionReceipt().get().getTransactionHash();
            collector.logStepPass("IntegerDataTypeContract issued successfully.contractAddress:" + contractAddress + ", hash:" + transactionHash);
*/

        } catch (Exception e) {
            if(e instanceof ArrayIndexOutOfBoundsException){
                collector.logStepPass("IntegerDataTypeContract and could not call contract function");
            }else{
                collector.logStepFail("IntegerDataTypeContract failure,exception msg:" , e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
