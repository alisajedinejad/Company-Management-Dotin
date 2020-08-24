<%@ page import="entity.CategoryEntity" %>
<%@ page import="entity.DayOffRequest" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%@ include file="./header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<%
    Random rand = new Random();
    int n = rand.nextInt(1000000000);


    List<CategoryEntity> CategoryEntities = (List<CategoryEntity>) request.getAttribute("CategoryEntities");
    List<User> users = (List<User>) request.getAttribute("users");
    List<DayOffRequest> requests = (List<DayOffRequest>) request.getAttribute("requests");
    User user = (User) request.getAttribute("user");
    String myHash = Integer.toString(n) + "userId" + user.getId();

%>
<div id="report">
    <img onclick="closeAlert()" class="hover" src="../assets/images/close-2.png">

    <div>
        <img>

        <p></p>
        <button onclick="closeAlert()">قبول</button>
    </div>
</div>
<div id="confirm">
    <img onclick="closeCorfirm()" class="hover" src="../assets/images/close-2.png">

    <div>
        <img src="../assets/images/question.png">

        <p></p>
        <button onclick="closeCorfirm('1')">بله</button>
        <button onclick="closeCorfirm('0')"> خیر</button>
    </div>
</div>

<div id="main">


    <div id="back"></div>
    <div id="header">
        <img id="profilePic" src="../assets/images/profiles-avatar.png">
        <img id="profilePic2" src="../assets/images/more.png">

    </div>
    <a href="#main">
        <div id="menu">

            <p id="profileName"> نا م کاربری :</p>

            <p id="profileName2"><% out.print(user.getName()); %></p>
            <%--<p id="profileName2"><% out.print(user.getRole().getCode());   %></p>--%>

            <% if (user.getRole().getCode().equals("admin")) { %>
            <div id="createUserIcon" onclick="openMenuItemes('createUser')" class="menuItems hover"><img
                    src="../assets/images/add-file.png">


                <p> ایجاد همکار جدید</p>


            </div>
            <% } %>
            <div id="vacationIcon" onclick="openMenuItemes('vacation')" class="menuItems hover"><img
                    src="../assets/images/vacation.png">

                <p> ثبت مرخصی</p></div>
            <div id="checkIcon" onclick="openMenuItemes('check')" class="menuItems hover"><img
                    src="../assets/images/check.png">

                <p> بررسی درخواست ها</p></div>
            <div id="mailIcon" onclick="openMenuItemes('mail')" class="menuItems hover"><img
                    src="../assets/images/mail.png">

                <p> ارسال ایمیل</p></div>
            <div id="profileIcon" onclick="openMenuItemes('profile')" class="menuItems hover"><img
                    src="../assets/images/user.png">

                <p>مشاهده ایمیل ها</p></div>


        </div>
    </a>

    <div id="menu2"></div>
    <div id="menu3"></div>
    <div id="menu4"></div>
    <img class="infinite hover" onclick="openMenu()" src="../assets/images/send.png" id="menu5">


    <div id="createUser">

        <form method="post" action="/ctl/insertEmployee">

            <div class="hover"><img src="../assets/images/userName.png"><input type="text" name="name"
                                                                               placeholder="نام همکار را وارد کنید">
            </div>
            <div class="hover"><img src="../assets/images/userName.png"><input type="text" name="firstName"
                                                                               placeholder="نام مستعار ">
            </div>
            <div class="hover"><img src="../assets/images/mail.png"><input type="text" name="email"
                                                                           placeholder="ایمیل را وارد کنید"></div>
            <div class="hover"><img src="../assets/images/passport.png"><input type="text" name="password"
                                                                               placeholder="پسوورد ورود همکار را وارد کنید">
            </div>
            <div class="hover">
                <img src="../assets/images/role.png">

                <select onclick="mySelect()" class="hover" name="role">

                    <option class="disable">نقش کاربر را مشخص کنید</option>

                    <% for (int i = 0; i < CategoryEntities.size(); i++) { %>
                    <option value="<% out.print(CategoryEntities.get(i).getId()); %>">
                        نقش :

                        <% out.print(CategoryEntities.get(i).getName()); %>
                    </option>
                    <% } %>
                </select>
            </div>
            <div class="hover">
                <img src="../assets/images/managar.png">

                <select onclick="mySelect()" class="hover" name="manager">

                    <option class="disable">لطفا مدیر مستقیم این همکار را مشخص کنید</option>

                    <% for (int i = 0; i < users.size(); i++) { %>
                    <option value="<% out.print(users.get(i).getId()); %>">
                        مدیر مستقیم :
                        <% out.print(users.get(i).getName()); %>

                    </option>
                    <% } %>
                </select>

            </div>

            <div class="hover">
                <button class="hover">ثبت</button>
            </div>

            <input name="userName" value="<% out.print(user.getEmail()); %>" hidden>
            <input name="password2" value="<% out.print(user.getPassword()); %>" hidden>


        </form>

        <div id="showAllClient">
            <a href="/ctl/seeAll">
                <button class="hover">مشاهده همه ی همکاران</button>
            </a>
        </div>

    </div>
    <div id="vacation">

        <form method="post" action="/ctl/checkOffDays">

            <p id="vacationTitle1">: شروع مرخصی</p>

            <p id="vacationTitle2">: پایان مرخصی</p>

            <div class="row" id="startTimeVacation">
                <div class="col-sm-6" style="text-align: left">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span style="border: none;background-color:transparent;width: 300px;height: 50px;z-index: 9"
                                  class="input-group-text cursor-pointer hover" id="dt_class"></span>
                        </div>
                        <input name="startVacationTime" type="text" id="inputDate3" class="form-control" value="">
                        <img src="../assets/images/calendar.png"
                             style="width: 26px;height: 26px;top:5px;left: 5px;position: absolute">
                    </div>
                    <label id="showDate_class"
                           style="width:300px;margin-left:0px;color: white;position: absolute;top: 60px;text-align: center;font-size: 17px"> </label>
                </div>
            </div>

            <div class="row" id="endTimeVacation">
                <div class="col-sm-6" style="text-align: left">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span style="border: none;background-color:transparent;width: 300px;height: 50px;z-index: 9"
                                  class="input-group-text cursor-pointer hover" id="dt_class4"></span>
                        </div>
                        <input name="endVacationTime" type="text" id="inputDate4" class="form-control" value="">
                        <img src="../assets/images/calendar.png"
                             style="width: 26px;height: 26px;top: 5px;left:5px;position: absolute">
                    </div>
                    <label id="showDate_class4"
                           style="width:300px;margin-left:0px;color: white;position: absolute;top: 60px;text-align: center;font-size: 17px"> </label>
                </div>
            </div>
            <input name="userId" value="<% out.print(user.getId()); %>" hidden>
            <button id="sendVacation" type="submit">ارسال درخواست مرخصی</button>


            <input name="userName" value="<% out.print(user.getEmail()); %>" hidden>
            <input name="password2" value="<% out.print(user.getPassword()); %>" hidden>
        </form>


        <form style="height: 0px" action="/ctl/showAllVacations" method="post">
            <input name="userId" value="<% out.print(user.getId()); %>" hidden>
            <button style="margin-left: -125px" type="submit" id="oldVacation">مشاهده درخواست های مرخصی قبلی</button>
        </form>

    </div>
    <div id="check">


        <div id="wrapper">
            <div class="scrollbar" id="style-2">
                <div class="force-overflow"></div>

                <table>
                    <th>نام</th>
                    <th>تاریخ شروع مرخصی</th>
                    <th>تاریخ پایان مرخصی</th>
                    <th>وضعیت</th>
                    <% for (int i = 0; i < requests.size(); i++) {
                        String name = "";
                        for (int j = 0; j < users.size(); j++) {
                            if (requests.get(0).getId() == users.get(j).getId()) {

                                name = users.get(j).getName();
                                break;
                            }

                        }


                    %>
                    <tr>
                        <td><% out.print(name); %></td>
                        <td><% out.print(requests.get(i).getStart()); %></td>
                        <td><% out.print(requests.get(i).getEnd()); %></td>
                        <td>

                            <span class="" id="accept">   پذیرفتن </span>


                            <span id="pending" class="">بلاتکلیف</span>

                            <span class="" id="deny">رد درخواست</span>


                            <div onclick="activeAccept('7',this,'<% out.print(i);%>','<% out.print(requests.get(i).getId());%>')"
                                 class="acceptD"
                                 id="acceptD<% out.print(i);%>"></div>
                            <div onclick="activeAccept('5',this,'<% out.print(i);%>','<% out.print(requests.get(i).getId());%>')"
                                 class="pendingD  activeAccept"
                                 id="pendingD<% out.print(i);%>"></div>
                            <div onclick="activeAccept('6',this,'<% out.print(i);%>','<% out.print(requests.get(i).getId());%>')"
                                 class="denyD"
                                 id="denyD<% out.print(i);%>"></div>


                        </td>

                    </tr>

                    <% } %>
                </table>
            </div>

        </div>


    </div>
    <div id="mail">


        <form action="/ctl/emailData" method="post">


            <p id="senderTitle">گیرندگان : </p>


            <div id="senders">
                <div class="scrollbar" id="style-3">
                    <div class="force-overflow2"></div>
                    <% for (User user1 : users) { %>
                    <div class="recivers">
                        <input type="checkbox" name="names" value="<% out.print(user1.getId()); %>"
                               onclick="return ValidatePetSelection();"> <% out.print(user1.getName()); %>
                    </div>
                    <br>
                    <br>


                    <% } %>
                </div>
            </div>


            <p id="subjectTitle">تیتر ایمیل :</p>

            <div id="subject">
                <input type="text" name="subject" placeholder="تیتر ایمیل ">
            </div>


            <p id="textTitle">متن ایمیل :</p>

            <div id="text">
                <textarea name="context">

                </textarea>
            </div>

            <input name="id" type="text" value="<% out.print(user.getId()); %>" hidden>
            <img id="sendMailIMG" height="40" width="40" src="../assets/images/sendMail.png">

            <p id="attachTitle">ضمیمه کردن فایل :</p>

            <input name="hashFIle" value="<% out.print(myHash); %>" hidden>
            <input name="userName" value="<% out.print(user.getEmail()); %>" hidden>
            <input name="password2" value="<% out.print(user.getPassword()); %>" hidden>

            <div id="attach" class="hover">
                <input id="file" name="file" class="hover" type="file" placeholder="انتخاب فایل">

                <div class="hover" id="chooseFile">انتخاب</div>
                <%--<button onclick="sendFile('<% out.print(user.getId()); %>')"  class="hover" id="senFile2">چسباندن فایل</button>--%>

            </div>
            <button type="submit" onclick="sendFile('<% out.print(myHash); %>')"
                    onmouseover="$('#sendMailIMG').addClass('sendMailAnim')"
                    onmouseout="$('#sendMailIMG').removeClass('sendMailAnim')" id="sendEmail">فرستادن
            </button>

            <!--<img id="saveMailIMG" height="20" width="20" src="../assets/images/save.png"  >-->

            <!--<button  onmouseover="$('#saveMailIMG').addClass('saveAnim')" onmouseout="$('#saveMailIMG').removeClass('saveAnim')"   id="saveEmail">ذخیره</button>-->

        </form>


    </div>
    <div id="profile">
        <form id="profileForm1" method="post" action="/ctl/sendsMail">

            <input name="userName" value="<% out.print(user.getEmail()); %>" hidden>
            <input name="password2" value="<% out.print(user.getPassword()); %>" hidden>
            <input name="userId" value="<% out.print(user.getId()); %>" hidden>
            <button class="hover" type="submit">مشاهده ایمیل های فرستاده شده</button>


        </form>


        <form id="profileForm2" method="post" action="/ctl/recivedMail">
            <input name="userName" value="<% out.print(user.getEmail()); %>" hidden>
            <input name="password2" value="<% out.print(user.getPassword()); %>" hidden>
            <input name="userId" value="<% out.print(user.getId()); %>" hidden>
            <button class="hover" type="submit">مشاهده ایمیل های رسیده</button>
        </form>
    </div>


</div>


<script>
    $(document).ready(function () {

        var today = new Date();
        $("#inputDate3").val(today);
        $("#inputDate4").val(today);

        $("#dt_class").MdPersianDateTimePicker({
            targetDateSelector: "#inputDate3",
            targetTextSelector: "#showDate_class",
            textFormat: " dddd dd MMMM yyyy ",
            isGregorian: false,
            modalMode: true
        });
        $("#dt_class4").MdPersianDateTimePicker({
            targetDateSelector: "#inputDate4",
            targetTextSelector: "#showDate_class4",
            textFormat: " dddd dd MMMM yyyy ",
            isGregorian: false,
            modalMode: true
        });
    });
</script>
<%@ include file="./footer.jsp" %>