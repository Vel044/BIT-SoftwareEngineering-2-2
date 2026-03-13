import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// 读取数据
val filePath = "/home/vel/mnt/data.txt"

// 加载数据并解析每行记录
val data = sc.textFile(filePath)
val parsedData = data.map(line => {
  val fields = line.split(",")
  val userId = fields(0).toInt
  val friendIds = fields.slice(1, fields.length - 3).map(_.toInt)
  val gender = fields(fields.length - 3)
  val age = fields(fields.length - 2).toInt
  val location = fields(fields.length - 1)
  (userId, friendIds, gender, age, location)
})

// 1
// 计算每个用户的好友数量
val friendCounts = parsedData.map{ case (userId, friendIds, _, _, _) => (userId, friendIds.length) }

// 找出前N个用户
val topNUsers = friendCounts.takeOrdered(10)(Ordering[Int].reverse.on(_._2))

// 输出结果
topNUsers.foreach(println)


// 2
// 定义年龄段函数
def ageGroup(age: Int): String = {
  if (age >= 18 && age <= 25) "18-25"
  else if (age >= 26 && age <= 35) "26-35"
  else if (age >= 36 && age <= 45) "36-45"
  else "Above 45"
}

// 计算每个用户的年龄段及其好友数量
val ageFriendCounts = parsedData.map{ case (_, friendIds, _, age, _) => (ageGroup(age), friendIds.length) }

// 按年龄段分组并计算平均好友数量
val avgFriendCountsByAge = ageFriendCounts.groupByKey().mapValues(friendCounts => friendCounts.sum.toDouble / friendCounts.size)

// 输出结果
avgFriendCountsByAge.collect().foreach(println)


// 3
// 计算每个用户的性别及其好友数量
val genderFriendCounts = parsedData.map{ case (_, friendIds, gender, _, _) => (gender, friendIds.length) }

// 按性别分组并计算平均好友数量
val avgFriendCountsByGender = genderFriendCounts.groupByKey().mapValues(friendCounts => friendCounts.sum.toDouble / friendCounts.size)

// 输出结果
avgFriendCountsByGender.collect().foreach(println)

// 4
// 计算每个用户的地理位置及其好友数量
val locationFriendCounts = parsedData.map{ case (_, friendIds, _, _, location) => (location, friendIds.length) }

// 找出每个地区最受欢迎的用户
val popularUsersByLocation = locationFriendCounts.reduceByKey(math.max)

// 输出结果
popularUsersByLocation.collect().foreach(println)
