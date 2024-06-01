package com.afa.devicer.core.utils;


import com.afa.devicer.core.errors.CoreException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class BaseUtils {

    public static String NVL(String value, String defValue) {
        return (StringUtils.isEmpty(value)) ? defValue : value;
    }

    public static <T> T NVL(T value, T defValue) {
        return (value == null) ? defValue : value;
    }

    /**
     * Parse value as Long
     *
     * @param text      text of field
     * @param fieldName
     * @param ifThrow   throw exception if parse failed
     * @return value as Long
     */
    public static Long parseLong(String text, String fieldName, boolean ifThrow) throws CoreException {
        if (StringUtils.isEmpty(text))
            return null;

        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            if (ifThrow)
                throw new CoreException(CoreException.PARSE_ERROR, BaseUtils.NVL(fieldName, "") + "=" + text, false);
            else
                log.error("parseLong failed {}={}", BaseUtils.NVL(fieldName, ""), text);

            return null;
        }
    }

    /**
     * Parse value as BigDecimal
     *
     * @param text
     * @param fieldName
     * @param ifThrow   throw exception if parse failed
     * @return value as BigDecimal
     */
    public static BigDecimal parseBigDecimal(String text, String fieldName, boolean ifThrow) throws CoreException {
        if (StringUtils.isEmpty(text))
            return null;

        try {
            return new BigDecimal(text.replace(',', '.'));
        } catch (NumberFormatException e) {
            if (ifThrow)
                throw new CoreException(CoreException.PARSE_ERROR, BaseUtils.NVL(fieldName, "") + "=" + text, false);
            else
                log.error("parseBigDecimal failed {}={}", BaseUtils.NVL(fieldName, ""), text);

            return null;
        }
    }

    public static Double doubleValue(final String val) throws CoreException {
        try {
            return Double.valueOf(val);
        } catch (NumberFormatException ex) {
            throw new CoreException(CoreException.PARSE_ERROR, val, false);
        }
    }

    public static String getVmFullInfo() {
        final StringBuilder sb = new StringBuilder();
        final String newLine = System.lineSeparator();

        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        sb.append("BootClassPath: ").append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getBootClassPath)).append(newLine)
                .append('\t').append("LibraryPath: ").append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getLibraryPath))
                .append(newLine).append('\t').append("Uptime: ").append(runtimeMXBean.getUptime()).append(newLine)
                .append('\t').append("VmName: ").append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getVmName))
                .append(newLine).append('\t').append("VmVendor: ")
                .append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getVmVendor)).append(newLine).append('\t')
                .append("VmVersion: ").append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getVmVersion)).append(newLine)
                .append('\t').append("isBootClassPathSupported: ").append(runtimeMXBean.isBootClassPathSupported())
                .append(newLine).append('\t').append("StartTime: ").append(new Date(runtimeMXBean.getStartTime()))
                .append(newLine).append('\t').append("InputArguments: ")
                .append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getInputArguments)).append(newLine).append('\t')
                .append("ManagementSpecVersion: ").append(runtimeMXBean.getManagementSpecVersion()).append(newLine)
                .append('\t').append("SpecName: ").append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getSpecName))
                .append(newLine).append('\t').append("SpecVendor: ")
                .append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getSpecVendor)).append(newLine).append('\t')
                .append("SpecVersion: ").append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getSpecVersion))
                .append(newLine).append('\t').append("SystemProperties: ")
                .append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getSystemProperties)).append(newLine).append('\t')
                .append("Name: ").append(runtimeMXBean.getName()).append(newLine).append('\t').append("ClassPath: ")
                .append(getBeanInfo(runtimeMXBean, RuntimeMXBean::getClassPath)).append(newLine).append('\t')
                .append("ObjectName: ").append(runtimeMXBean.getObjectName());

        return sb.toString();
    }

    private static Object getBeanInfo(final RuntimeMXBean runtimeMXBean,
                                      final Function<RuntimeMXBean, Object> function) {
        final Object object;
        try {
            object = function.apply(runtimeMXBean);
        } catch (final UnsupportedOperationException | SecurityException e) {
            return e.getMessage();
        }

        return object;
    }

    public static String buildHexDump(byte[] bytes) {
        return buildHexDump(bytes, 16, 2048);
    }

    public static String buildHexDump(byte[] bytes, int numberOfBytes, int limitBytes) {
        if (bytes == null)
            return "";

        StringBuilder buffer = new StringBuilder();
        int lineNumber = 0;
        int counter = 0;
        while (bytes.length > counter && counter <= limitBytes) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder(" ");
            buffer.append(String.format("%04X  ", lineNumber * numberOfBytes));
            for (int j = 0; j < numberOfBytes; j++) {

                if (counter < bytes.length) {
                    byte value = bytes[counter];
                    counter++;
                    sb1.append(String.format("%02x ", 0xFF & value));
                    if ((0xFF & value) > 0x1F) {
                        sb2.append(((char) (0xFF & value)));
                    } else {
                        sb2.append(".");
                    }
                } else {
                    for (; j < numberOfBytes; j++) {
                        sb1.append("   ");
                    }
                }
            }
            buffer.append(sb1);
            buffer.append(sb2);
            buffer.append(String.format("%n"));
            lineNumber++;
        }
        if (counter > limitBytes) {
            buffer.append("...");
            buffer.append(String.format("%n"));
        }
        return buffer.toString();
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] chunk = new byte[4096];
        while ((nRead = in.read(chunk, 0, chunk.length)) != -1) {
            buffer.write(chunk, 0, nRead);
        }
        buffer.flush();

        return buffer.toByteArray();
    }

    public static String traceBigInteger(BigInteger v) {
        byte[] b = v.toByteArray();

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            if ((b[i] & 0xff) < 0x10)
                buf.append("0");

            buf.append(Integer.toHexString(b[i] & 0xff).toUpperCase());
            if (i != b.length - 1)
                buf.append(" ");
        }

        return buf.toString();
    }

    public static final String traceArray(final Object[] array, final boolean isTraceBarkets) {
        if (array != null && array.length == 0)
            return isTraceBarkets ? "[]" : "";

        final StringBuilder text = new StringBuilder();
        return traceArray(array, text, isTraceBarkets).toString();
    }

    public static final StringBuilder traceArray(final Object[] array, final StringBuilder text, final boolean isTraceBarkets) {
        return traceArray(array, text, isTraceBarkets ? "[" : null, isTraceBarkets ? "]" : null);
    }

    public static final StringBuilder traceArray(final Object[] array, final StringBuilder text, final String startBraket, final String endBraket) {
        if (array == null) {
            text.append("NULL");
            return text;
        }

        if (startBraket != null)
            text.append(startBraket);

        boolean isNotFirst = false;
        for (Object data : array) {
            if (isNotFirst)
                text.append(",");
            else
                isNotFirst = true;

            text.append(data);
        }

        if (endBraket != null)
            text.append(endBraket);

        return text;
    }

    public static String updateValue(final String str, final String tag, String replace) {
        return str.replaceAll("<" + tag + ">(.*)</" + tag + ">", replace);
    }

    public static Date truncateDate(final Date date) {
        if (date == null)
            return null;

        Date result;
        final ZoneId zone = ZoneId.systemDefault();
        final LocalDateTime toDate = LocalDateTime.ofInstant(date.toInstant(), zone);
        ZonedDateTime dateTime = ZonedDateTime.of(toDate.toLocalDate(),
                LocalTime.of(0, 0, 0, 0), zone);

        result = Date.from(dateTime.toInstant());
        return result;
    }

    public static GregorianCalendar createTruncCalendar(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        return new GregorianCalendar(y, m, d);
    }

    public static GregorianCalendar createCalendar(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar;
    }
}
