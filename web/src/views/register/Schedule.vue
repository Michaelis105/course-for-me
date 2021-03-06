<template>
  <v-container fluid>
    <v-container>
      <v-form>
        <v-container>
          <v-row>
            <v-col cols="3">
              <v-card>
                <v-subheader>Added Course Cart</v-subheader>
                <v-data-table v-model="selected" :headers="courseCartHeaders" :items="courseCartItems" item-key="number" :items-per-page="5" class="elevation-1" :single-select=true show-select>
                </v-data-table>
              </v-card>
              <v-row>
                <v-checkbox v-model="daysSelected" label="M" value="M"></v-checkbox>
                <v-checkbox v-model="daysSelected" label="T" value="T"></v-checkbox>
                <v-checkbox v-model="daysSelected" label="W" value="W"></v-checkbox>
                <v-checkbox v-model="daysSelected" label="R" value="R"></v-checkbox>
                <v-checkbox v-model="daysSelected" label="F" value="F"></v-checkbox>
              </v-row>
              <v-checkbox v-model="openOnly" label="Open Seats Only" value="open"></v-checkbox>
              <v-btn color="primary" @click="searchClasses()">Search</v-btn>

              <!--
              Starting/End Time
              Instructor
              -->

            </v-col>

            <v-col cols="9">
              <v-card>
                <v-subheader>Searched Classes</v-subheader>
                <v-data-table :headers="classTableHeaders" :items="classItems" :items-per-page="5" class="elevation-1">
                  <template v-slot:item.actions="{ item }">
                    <v-icon small class="mr-2" @click="addClassToCart(item)">mdi-plus</v-icon>
                  </template>
                </v-data-table>
              </v-card>
            </v-col>
            
          </v-row>

          <v-card>
            <v-subheader>Added Class Cart</v-subheader>
            <v-data-table :headers="classCartTableHeaders" :items="classCartItems" :items-per-page="5" class="elevation-1">
              <template v-slot:item.actions="{ item }">
                <v-icon small class="mr-2" @click="removeClassFromCart(item)">mdi-minus</v-icon>
              </template>
              <template v-slot:no-data>
                Empty cart - add some classes
              </template>
            </v-data-table>
          </v-card>


          <!--
          <v-row>
            <v-col>
              <v-card>
                <v-subheader>Proposed Schedule</v-subheader>
                <v-data-table :headers="classTableHeaders" :items-per-page="5" class="elevation-1"></v-data-table>
              </v-card>
              <v-sheet height="400">
                <v-calendar ref="calendar" v-model="value" first-time="07:00" intervalMinutes="15" type="week"></v-calendar>
              </v-sheet>
            </v-col>
          </v-row>
        -->
        </v-container>
      </v-form>
    </v-container>
    <ScheduleChangeConfirmationDialog :enrollClasses="classCartItems" :unenrollClasses="removeClassesCart"></ScheduleChangeConfirmationDialog>
    <v-snackbar v-model="snackbar" :timeout=2000>
      {{ snackbarText }}
    </v-snackbar>
  </v-container>
</template>

<script>
  import ScheduleChangeConfirmationDialog from '../../components/ScheduleChangeConfirmationDialog';

  export default {
    name: 'RegisterSchedule',
    components: {
      ScheduleChangeConfirmationDialog
    },
    props: [ 'cartCourses', 'cartClasses' ],
    data () {
      return {
        ready: false,
        hasSaved: false,
        isEditing: null,
        model: null,
        selected: null,
        snackbar: false,
        snackbarText: "",
        daysSelected: ['M', 'T', 'W', 'R', 'F'],
        openOnly: false,

        classTableHeaders: [
          { text: 'Class ID', value: 'classId' },
          { text: 'Instructor', value: 'instructor' },
          { text: 'Meeting Times', value: 'meeting' },
          { text: 'Availability', value: 'availability' },
          { text: 'Waitlist', value: 'waitlist' },
          { text: 'Actions', value: 'actions' },
        ],
        classItems: [],

        courseCartHeaders: [
            { text: 'Subject', value: 'subject' },
            { text: 'Course Number', value: 'number' },
        ],
        courseCartItems: [],

        classCartTableHeaders: [
          { text: 'Class ID', value: 'classId' },
          { text: 'Subject', value: 'subject' },
          { text: 'Course Number', value: 'number' },
          { text: 'Title', value: 'courseTitle' },
          { text: 'Instructor', value: 'instructor' },
          { text: 'Meeting Times', value: 'meeting' },
          { text: 'Credit Hours', value: 'creditHours' },
        ],
        classCartItems: [],
        removeClassesCart: [],
      }
    },
    mounted () {
      this.ready = true
      if (this.cartCourses) {
        this.courseCartItems = this.cartCourses
      }
      if (this.cartClasses) {
        this.classCartItems = this.cartClasses
      }
    },
    methods: {
      searchClasses() {
        this.classItems = []
        if (this.selected == null) {
          this.snackbarText = "Please select a course to search from your cart."
          this.snackbar = true
        } else {
          this.url = "http://michaellouie.net/api/v1/class"
          this.url += "?c=" + this.selected[0].number
          if (this.daysSelected != null && this.daysSelected.length > 0) {
            this.url += "&d=" + this.daysSelected
          }

          this.url += "&a=" + (this.openOnly ? "True" : "False")
          
          fetch(this.url, {
            method: "GET"
          })
          .then(response => response.json())
          .then((data) => {
            for (var i = 0; i < data.length; i++) {
              var curClass = data[i]
              var time = ""
              
              for (var j = 0; j < curClass.classTimes.length; j++) {
                var classTime = curClass.classTimes[j]
                time += classTime.day + " " + classTime.startTime + " - " + classTime.endTime + "\n";
              }
              
              var avail = curClass.seatOccupied + " of " + curClass.seatCapacity;
              var wait = curClass.seatWaitlistOccupied + " of " + curClass.seatWaitlistCapacity;
              var aClass = {
                  "classId": curClass.classId,
                  "courseId": curClass.courseId,
                  "instructor": curClass.instructor,
                  "availability": avail,
                  "meeting": time,
                  "waitlist": wait
              }
              this.classItems.push(aClass)
            }
          })
        }
      },
      addClassToCart(aClass) {
        var indexOfClass = this.classCartItems.indexOf(aClass)
        if (indexOfClass == -1) {
          this.classCartItems.push(aClass)
        } else {
          this.snackbarText = "Class already added!"
          this.snackbar = true
        }
      },
      removeClassFromCart(aClass) {
        this.classCartItems.splice(this.classCartItems.indexOf(aClass), 1)
      }
    },
  }
</script>

<style>
.v-calendar-daily_head-day-label {
    display: none;
}
</style>