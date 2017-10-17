﻿package com.sc.tradmaster.utils.config.alipay;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2017052507343438";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnOadCtKxxLdntoeOH0rtTHPbRxR4J85QXxnhQeI+WLVUQug9yBAAXUWpS0H6tfG2rTjNU78m09EWKayPRVXG7ZB48ua2AUjmF69XUd+RE6N7QyED9+0R2T7WPtwMZPnOXrS57AZUyX3QZXPeeVIY8dHbIGVp7foklUyEpcK9e73BRfGHw1mYgz4NZrq+a6VPa6NYBUJ/E+0D1A+Gy1cPYrUlLzzlgCrJaWFUHneAs/X8aNKYb+9PZhMUlTqsCtOOUgROqdhaUNZSU7FB6oueZ06gW3deDgMDjICf4Q6bNxDXeOmkMgqbgOvxBI0SGSEN1rZGMrwTyXblxZ+6TTB6XAgMBAAECggEBAIvI/s8c9lT5jgU9YVRFTH9yY8QAMVcCPNYO+r3Rub1Aka4Uyhp1y5myXd1yGaJts8MR8IjclNDlrEfDvcIb2aFaVIhQcf8lPJ59AFbGDZOo0bDW5LUqWBVKFixXQIjWkovEoWkDbrMX5ZXaB8dbQobAmBY2U1ON95iyv7LDVmVVAzCChfucYcmDWtjZxrN/tMNK4gvQY3js/unMx7Gg/GDFU7q7S9XTpSc0U579/MJsVRxI1Qx2QmdWhnwwS8b5jo61Fh7ZkJdIC+JpwjGzHz+U1S1sCyunkhpIbMPmLOTy0aAf0aW4vs/mwDhpxVNU1/y5jNFzFR0jhFkw+ecYLZECgYEA+yu9/5UEy5Zf/G3+jKS9ZbtfxuQ9tZqTYUHdh6zB4q3oO1pEpWrvAh1nJ4VqtK4NDndauNd6Vyqxo1h9xCvpDdocVQ/+Yrr0VmzGYq3KiWEwJmZIu+VuKCAfdYvp6UTVPA+KmiZy5oXo/NX7glICaN/ZarBPVwYDKVYVjE5zQd8CgYEAqnC7fUUOEfks4kpfrxWZpVnQVZ72SpYk053OGGwpE4A7Ck7DNQoM20IbsYE9NDa2kms+OLPCPIxQqcadsgFKcAAud/nqK3t+P3CTjeKT54iWn9McLq4S+yW7JOG7QdOSW/pHZ840QRTD1yHMgrBD1pscgXcdh9sp4KRrwfnYakkCgYEA39J2CKih0k5ZkNfwCyLOyDY2PkWsHAX4KaYJJFs1l6uDlEh3fbUR8Z+ECfrhQMsNRYPkq20RT1XzYG19/W9nyflAYCjVduPE0rMyAkN+hfe+camaTR5BkK43eodI1Z+KKXBsAbwbjgNswMpqyxILpas80s70lMb90DsvDo3mNZ8CgYEAlRVomkmF5ijLnuCpU2+UZrLp7YrPKiys58F33Zr2Tw1yFzEPxNwWpM7thFVkoMBe8DIISfbKpsWhZO/LTkGcx90QyHb84q3xnzIsjwYqk3LBNemmFBrFlZLKYJdmgUy8D+IbBWyz/l1YdFwnFAV0QUqKI6B6XzRQLCQF8bVGHKkCgYBXcWZYbfhjg6P1BNwmW+7ESLcztH9GrlleXkNdggsL+xiX1NzujxLgoMrdphkX3bfS8sd3URmig5WCN88UusTmrsy/ufatdK6aflP+t0Nod5xByCcrHwB1T6/vKfkCazp81QVHXAKvHCu57UzEWnLXgIjeFscaX5Xlvl43kGmocg==";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "https://openapi.alipay.com/gateway.do";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://www.housesline.com/paysuccess";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}