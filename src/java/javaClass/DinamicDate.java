/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ENAMUL
 */
public class DinamicDate {

    /**
     * @param args the command line arguments
     */
    private String sDay, sDay2, cDay, cDay2, cDay3;
    private int day, m, y;
    private String[] dates = new String[10];

    public String[] dinamicDate() {
        Date date = new Date();
        //DateFormat df2 = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss day");
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String s = df.format(date);
        //String s2 = df2.format(date);
        //System.out.println(s2);
        dates[0] = s;
        y = Integer.parseInt(s.substring(0, 4));
        m = Integer.parseInt(s.substring(5, 7));
        day = Integer.parseInt(s.substring(8));
        sDay = s.substring(0, 8);
        sDay2 = s.substring(0, 5);
        //System.out.println(sDay2);
        //System.out.println(day+"..."+m+"...."+y);
        //...........less then equal 20...........................
        if (day < 20) {
            for (int i = 0; i < 9; i++) {
                day += 1;
                cDay = String.valueOf(day);
                cDay2 = String.valueOf(m);
                if (m < 10) {
                    cDay3 = sDay2 + "0" + cDay2 + "-";
                } else {
                    cDay3 = sDay2 + cDay2 + "-";
                }
                if (day < 10) {
                    cDay3 += "0" + cDay;
                } else {
                    cDay3 += cDay;
                }
                dates[i + 1] = cDay3;
                //System.out.println(cDay2);
            }
            day = 0;
        }
        //System.out.println("4th Date "+dates[0]);
//        //...........20...........................
        if (day == 20) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 9; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[9] = cDay3;
                    //System.out.println(cDay3);
                }
            } else {
                for (int i = 0; i < 9; i++) {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = sDay + cDay;
                    dates[i + 1] = cDay2;
                    //System.out.println(cDay2);
                }
            }
            day = 0;
        }
//         //...........21...........................
        if (day == 21) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[9] = cDay3;
                    //System.out.println(cDay3);                                
                } else {
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 8] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                for (int i = 0; i < 9; i++) {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = sDay + cDay;
                    dates[i + 1] = cDay2;
                    //System.out.println(cDay2);
                }
            }
            day = 0;
        }
//        //...........22...........................
        if (day == 22) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 8] = cDay3;
                        //System.out.println(cDay3);   
                    }

                } else {
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 7] = cDay3;
                        //System.out.println(cDay3);   
                    }

                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 9; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }

                } else {

                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[9] = cDay3;
                    //System.out.println(cDay3);                  
                }

            }
            day = 0;
        }
//        //...........23...........................
        if (day == 23) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 7] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 6] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[9] = cDay3;
                    //System.out.println(cDay3);        

                } else {

                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 8] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
//     //...........24...........................
        if (day == 24) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 6] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 5] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 8] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {

                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 7] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
//        //...........25...........................
        if (day == 25) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 5] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 4] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 7] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {

                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 6] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
//         //...........26...........................
        if (day == 26) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 4] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 3] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 6] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {

                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 5] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
//        //...........27...........................
        if (day == 27) {
            if (m == 2) {
                if (y % 4 == 0) {
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 3] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = sDay + cDay;
                    dates[1] = cDay2;
                    //System.out.println(cDay2);                
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 2] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 4; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 5; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 5] = cDay3;
                        // System.out.println(cDay3);   
                    }
                } else {

                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 4] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
//        //...........28...........................
        if (day == 28) {
            if (m == 2) {
                if (y % 4 == 0) {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = sDay + cDay;
                    dates[1] = cDay2;
                    //System.out.println(cDay2);

                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 2] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {

                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 9; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 1] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 3; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 6; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 4] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {

                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 3] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
        //...........29...........................
        if (day == 29) {
            if (m == 2) {
                //add new month and day
                m += 1;
                day = 0;
                for (int i = 0; i < 9; i++) {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[i + 1] = cDay3;
                    //System.out.println(cDay3);   
                }
            } else {
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    for (int i = 0; i < 2; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = sDay + cDay;
                        dates[i + 1] = cDay2;
                        //System.out.println(cDay2);
                    }
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 7; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 3] = cDay3;
                        //System.out.println(cDay3);   
                    }
                } else {
                    day += 1;
                    cDay = String.valueOf(day);
                    if (day < 10) {
                        cDay2 = sDay + "0" + cDay;
                    } else {
                        cDay2 = sDay + cDay;
                    }
                    dates[1] = cDay2;
                    //System.out.println(cDay2);                
                    //add new month and day
                    m += 1;
                    day = 0;
                    for (int i = 0; i < 8; i++) {
                        day += 1;
                        cDay = String.valueOf(day);
                        cDay2 = String.valueOf(m);
                        if (m < 10) {
                            cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                        } else {
                            cDay3 = sDay2 + cDay2 + "-0" + cDay;
                        }
                        dates[i + 2] = cDay3;
                        //System.out.println(cDay3);   
                    }
                }
            }
        }
//        //...........30...........................

        if (day == 30) {
            if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {

                day += 1;
                cDay = String.valueOf(day);
                cDay2 = sDay + cDay;
                dates[1] = cDay2;
                //System.out.println(cDay2);

                //add new month and day
                m += 1;
                day = 0;
                for (int i = 0; i < 8; i++) {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[i + 2] = cDay3;
                    //System.out.println(cDay3);   
                }
            } else {
                //add new month and day
                m += 1;
                day = 0;
                for (int i = 0; i < 9; i++) {
                    day += 1;
                    cDay = String.valueOf(day);
                    cDay2 = String.valueOf(m);
                    if (m < 10) {
                        cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                    } else {
                        cDay3 = sDay2 + cDay2 + "-0" + cDay;
                    }
                    dates[i + 1] = cDay3;
                    //System.out.println(cDay3);   
                }
            }
        }
        //..........31..................
        if (day == 31) {
            //add new month and day
            day = 0;
            m += 1;
            for (int i = 0; i < 9; i++) {
                day += 1;
                cDay = String.valueOf(day);
                cDay2 = String.valueOf(m);
                if (m < 10) {
                    cDay3 = sDay2 + "0" + cDay2 + "-0" + cDay;
                } else {
                    cDay3 = sDay2 + cDay2 + "-0" + cDay;
                }
                dates[i + 1] = cDay3;
                //System.out.println(cDay3);   
            }
        }
        return dates;
    }
}
