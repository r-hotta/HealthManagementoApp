package com.example.healthmanagementoapp.form;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.healthmanagementoapp.dao.EntFormDao;
import com.example.healthmanagementoapp.dao.EntFormDao2;
import com.example.healthmanagementoapp.entity.Ent;

@EnableScheduling
@Controller
public class HealthController {
	private EntFormDao entformdao = null;
	private EntFormDao2 entformdao2 = null;

	@Autowired
	public void FormController(EntFormDao entformdao, EntFormDao2 entformdao2) {
		this.entformdao = entformdao;
		this.entformdao2 = entformdao2;
		
	}
	@RequestMapping("/home")
	public String home(Model model, Input input) {
		model.addAttribute("title", "健康管理システム");

		return "home";

	}
	
	@RequestMapping("/form")
	public String form(Model model, Input input, Input2 input2) {
		model.addAttribute("title", "健康者一覧ページ");
		List<Ent> list = entformdao.searchDb();
		
		model.addAttribute("dbList", list);
		model.addAttribute("dbList2", list);

		return "form";

	}
	@RequestMapping("/batform")
	public String batform(Model model, Input input) {
		model.addAttribute("title", "再診者一覧ページ");
		List<Ent> list = entformdao2.searchDb();
		
		model.addAttribute("dbList", list);
		model.addAttribute("dbList2", list);

		return "batform";

	}
	
	@RequestMapping("/day")
	public String day(Long id, String name, Input input, Input2 input2, Model model) {
		model.addAttribute("title", "受診日");
		
		List<Ent> list = entformdao.selectOne(id);
		
		model.addAttribute("dbList", list);
		
		return "day";
	}
	@RequestMapping("/day2")
	public String day2(Long id, String name, Input input, Input2 input2, Model model) {
		model.addAttribute("title", "受診日");
		
//		List<Ent> list = entformdao2.searchDb();
		List<Ent> list = entformdao2.selectOne(id);
		model.addAttribute("dbList", list);
		return "day2";
	}
	
	@RequestMapping("/dayk")
	public String dayk(Long id, String name, Input input, Input2 input2, Model model) {
		model.addAttribute("title", "受診日");
		List<Ent> list2 = entformdao.selectOne(id);
		List<Ent> list = entformdao.selectOne(name);
		model.addAttribute("dbList", list);
		model.addAttribute("dbList2", list2);
		
		return "dayk";
	}
	@RequestMapping("/dayk2")
	public String dayk2(Long id, String name, Input input, Input2 input2, Model model) {
		model.addAttribute("title", "受診日");
		List<Ent> list2 = entformdao2.selectOne(id);
		List<Ent> list = entformdao2.selectOne(name);
		model.addAttribute("dbList", list);
		model.addAttribute("dbList2", list2);
		
		return "dayk2";
	}
	
	@RequestMapping("/add")
	public String add(Model model, Input input) {
		model.addAttribute("title", "入力ページ");

		return "add";

	}
	@RequestMapping("/kennsaku")
	public String kennsaku(Model model, Input input, Long id) {
List<Ent> list = entformdao.selectOne(id);
		
		model.addAttribute("dbList", list);
		return "kennsaku";
	}
	@RequestMapping("/kennsaku2")
	public String kennsaku2(Model model, Input input, Long id) {
List<Ent> list = entformdao2.selectOne(id);
		
		model.addAttribute("dbList", list);
		return "kennsaku2";
	}
	
	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			return "add";
		}

		model.addAttribute("title", "入力内容確認ページ");
		return "confirm";
	}

	@RequestMapping("/complete")
	public String complete(Model model, Input input, Long id, Input2 input2) {
		model.addAttribute("title", "完了ページ");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		SimpleDateFormat day= new SimpleDateFormat("MM/dd");

		Ent entform = new Ent();
		
		String health = "異常なし";
		
		entform.setHiduke(day.format(date));	
		entform.setType(health);
		input.setHiduke(entform.getHiduke());
		entform.setName(input.getName());
		entform.setSeibetu(input.getSeibetu());
		entform.setAge(input.getAge());
		entform.setSinntyou(input.getSinntyou());
		entform.setTaijuu(input.getTaijuu());
		entform.setKetuatuue(input.getKetuatuue());
		entform.setKetuatusita(input.getKetuatusita());
		entform.setMemo(input.getMemo());
		entform.setHiduke(input.getHiduke());

		entformdao.insertDb(entform);
		
		System.out.println(input.getHiduke());
		return "complete";

	}
	@RequestMapping("/complete2")
	public String complete2(Model model, Input input) {
		model.addAttribute("title", "完了ページ");
		Ent entform = new Ent();
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		SimpleDateFormat day= new SimpleDateFormat("MM/dd");
		String health = "異常あり";
		
		entform.setHiduke(day.format(date));
		entform.setType(health);
		input.setHiduke(entform.getHiduke());
		entform.setName(input.getName());
		entform.setSeibetu(input.getSeibetu());
		entform.setAge(input.getAge());
		entform.setSinntyou(input.getSinntyou());
		entform.setTaijuu(input.getTaijuu());
		entform.setKetuatuue(input.getKetuatuue());
		entform.setKetuatusita(input.getKetuatusita());
		entform.setMemo(input.getMemo());
		entform.setHiduke(input.getHiduke());

		entformdao2.insertDb(entform);

		return "complete2";

	}
	@RequestMapping("/kannryou")
	public String kannryou(Model model, Input input, Input2 input2, Long id, String name) {
		model.addAttribute("title", "再診登録完了ページ");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		SimpleDateFormat day= new SimpleDateFormat("MM/dd");

		Ent entform = new Ent();
		String health = "異常なし";
		
		entform.setHiduke(day.format(date));	
		entform.setType(health);
		input.setHiduke(entform.getHiduke());
		entform.setName(input.getName());
		entform.setSeibetu(input.getSeibetu());
		entform.setAge(input.getAge());
		entform.setSinntyou(input.getSinntyou());
		entform.setTaijuu(input.getTaijuu());
		entform.setKetuatuue(input.getKetuatuue());
		entform.setKetuatusita(input.getKetuatusita());
		entform.setMemo(input.getMemo());
		entform.setHiduke(input.getHiduke());

		entformdao.updateDb(id, entform);
		
		System.out.println(input.getHiduke());
		return "kannryou";

	}
	@RequestMapping("/kannryou2")
	public String kannryou2(Model model, Input input, Long id, String name) {
		model.addAttribute("title", "完了ページ");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		SimpleDateFormat day= new SimpleDateFormat("MM/dd");

		Ent entform = new Ent();
		
		String health = "異常あり";
		
		entform.setHiduke(day.format(date));	
		entform.setType(health);
		input.setHiduke(entform.getHiduke());
		entform.setName(input.getName());
		entform.setSeibetu(input.getSeibetu());
		entform.setAge(input.getAge());
		entform.setSinntyou(input.getSinntyou());
		entform.setTaijuu(input.getTaijuu());
		entform.setKetuatuue(input.getKetuatuue());
		entform.setKetuatusita(input.getKetuatusita());
		entform.setMemo(input.getMemo());
		entform.setHiduke(input.getHiduke());
		
		entformdao2.updateDb(id, entform);
		
		System.out.println(input.getHiduke());
		return "redirect:/batform";

	}
	
	//削除(DELETE)
		@RequestMapping("/del/{id}")
		public String destory(@PathVariable Long id) {
			entformdao.deleteDb(id);
//			entformdao2.deleteDb(id);
			return "redirect:/form";
		}

		//削除(DELETE)
		@RequestMapping("/del2/{id}")
		public String destory2(@PathVariable Long id) {
			entformdao2.deleteDb(id);
			return "redirect:/batform";
		}
		//削除(DELETE)
		@RequestMapping("/alldel")
		public String alldel() {

			entformdao.alldeleteDb();
//			entformdao2.alldeleteDb();
			return "redirect:/form";
		}
				//削除(DELETE)
				@RequestMapping("/alldel2")
				public String alldel2() {
					
//					entformdao.alldeleteDb();
					entformdao2.alldeleteDb();
					return "redirect:/batform";
				}
		
		@RequestMapping("/cha/{id}")
		public String change(@PathVariable Long id, Model model) {
			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao.selectOne(id);
			
			//リストから、オブジェクトだけをピックアップ
			Ent entform = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entform);
			model.addAttribute("title", "判定更新ページ");
			model.addAttribute("dbList", list);

			return "change";
		}
		@RequestMapping("/cha/{id}/exe")
		public String changeExe(@PathVariable  Long id, Input input) {
			
			//フォームの値をエンティティに入れ直し
			
			Ent entform = new Ent();
			
			String health = "異常あり";

//			input.setHiduke(entform.getHiduke());
			entform.setType(health);
			entform.setName(input.getName());
			entform.setSeibetu(input.getSeibetu());
			System.out.println(entform.getSeibetu()); 
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			
			entform.setHiduke(input.getHiduke());

			entformdao.deleteDb(id);
			entformdao2.insertDb(entform);
			//一覧画面へリダイレクト
			return "redirect:/form";
		}
		@RequestMapping("/cha2/{id}")
		public String change2(@PathVariable Long id, Model model) {
			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao2.selectOne(id);
			//リストから、オブジェクトだけをピックアップ
			Ent entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "判定更新ページ");
			model.addAttribute("dbList", list);

			return "change2";
		}
		@RequestMapping("/cha2/{id}/exe")
		public String changeExe2(@PathVariable  Long id, Input input) {
			
			//フォームの値をエンティティに入れ直し
			
			Ent entform = new Ent();
			
			String health = "異常なし";

			entform.setType(health);
			entform.setName(input.getName());
			entform.setSeibetu(input.getSeibetu());
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			entform.setHiduke(input.getHiduke());
			
			entformdao.insertDb(entform);
			entformdao2.deleteDb(id);
			
			//一覧画面へリダイレクト
			return "redirect:/batform";
		}
		//更新画面の表示(SELECT)
		@RequestMapping("/edit/{id}")
		public String editView(@PathVariable Long id, Model model) {

			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			Ent entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "編集ページ");
			model.addAttribute("dbList", list);
			return "edit";
		}

		//更新処理(UPDATE)
		@RequestMapping("/edit/{id}/exe")
		public String editExe(@PathVariable @Validated  Long id , Model model, Input input, BindingResult result) {
			
			
			if (result.hasErrors()) {
				model.addAttribute("title", "入力ページ");
				return "edit";
			}
			//フォームの値をエンティティに入れ直し
			Ent entform = new Ent();
			String health = "異常なし";
			
			entform.setType(health);
			entform.setName(input.getName());
			entform.setSeibetu(input.getSeibetu());
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			entform.setHiduke(input.getHiduke());
			System.out.println(entform.getHiduke());
			System.out.println(input.getHiduke());
			//更新の実行
			entformdao.updateDb(id, entform);
			//一覧画面へリダイレクト
			return "redirect:/form";
		}

		@RequestMapping("/edit2/{id}")
		public String editView2(@PathVariable Long id, Model model) {

			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao2.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			Ent entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "編集ページ");
			model.addAttribute("dbList", list);
			return "edit2";
		}

		//更新処理(UPDATE)
		@RequestMapping("/edit2/{id}/exe")
		public String editExe2(@PathVariable @Validated Long id,Model model, Input input, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("title", "入力ページ");
				return "edit2";
			}
			
			//フォームの値をエンティティに入れ直し
			Ent entform = new Ent();

			String health = "異常あり";
			
			entform.setType(health);
			entform.setName(input.getName());
			entform.setSeibetu(input.getSeibetu());
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			entform.setHiduke(input.getHiduke());
			
			//更新の実行
			entformdao2.updateDb(id, entform);

			//一覧画面へリダイレクト
			return "redirect:/batform";
		}
		
		@RequestMapping("/saisin/{id}")
		public String saisin(@PathVariable Long id, Model model) {

			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			Ent entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "再登録ページ");
			model.addAttribute("dbList", list);
			return "saisin";
		}
		@RequestMapping("/saisin/{id}/exe")
		public String saisinExe(@PathVariable @Validated Long id,Model model, Input input, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("title", "入力ページ");
				return "saisin";
			}
			Calendar calendar = Calendar.getInstance();
			Date date = calendar.getTime();

			SimpleDateFormat day= new SimpleDateFormat("MM/dd");

			Ent entform = new Ent();
			
			String health = "異常あり";
			
			List<Ent> list = entformdao.selectOne(id);
			model.addAttribute("dbList", list);
			entform.setHiduke(day.format(date));	
			entform.setType(health);
			input.setHiduke(entform.getHiduke());
			entform.setName(input.getName());
			entform.setSeibetu(input.getSeibetu());
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			entform.setHiduke(input.getHiduke());
			//更新の実行
			entformdao.updateDb(id, entform);

			//一覧画面へリダイレクト
			return "kannryou";
		}
		
		@RequestMapping("/saisin2/{id}")
		public String saisin2(@PathVariable Long id, Model model) {

			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao2.selectOne(id);

			//リストから、オブジェクトだけをピックアップ
			Ent entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "再登録ページ");
			model.addAttribute("dbList", list);
			return "saisin2";
		}
		@RequestMapping("/saisin2/{id}/exe")
		public String saisinExe2(@PathVariable @Validated Long id,Model model, Input input, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("title", "入力ページ");
				return "saisin2";
			}
			Calendar calendar = Calendar.getInstance();
			Date date = calendar.getTime();

			SimpleDateFormat day= new SimpleDateFormat("MM/dd");

			Ent entform = new Ent();
			
			String health = "異常あり";
			
			List<Ent> list = entformdao2.selectOne(id);
			model.addAttribute("dbList", list);
			entform.setHiduke(day.format(date));	
			entform.setType(health);
			input.setHiduke(entform.getHiduke());
			entform.setName(input.getName());
			entform.setSeibetu(input.getSeibetu());
			entform.setAge(input.getAge());
			entform.setSinntyou(input.getSinntyou());
			entform.setTaijuu(input.getTaijuu());
			entform.setKetuatuue(input.getKetuatuue());
			entform.setKetuatusita(input.getKetuatusita());
			entform.setMemo(input.getMemo());
			entform.setHiduke(input.getHiduke());
			//更新の実行
			entformdao2.updateDb(id, entform);

			//一覧画面へリダイレクト
			return "kannryou2";
		}
		
}

