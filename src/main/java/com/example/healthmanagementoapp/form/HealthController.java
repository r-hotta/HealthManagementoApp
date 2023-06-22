package com.example.healthmanagementoapp.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.healthmanagementoapp.dao.EntFormDao;
import com.example.healthmanagementoapp.dao.EntFormDao2;
import com.example.healthmanagementoapp.entity.Ent;

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
		model.addAttribute("title", "タイトルページ");

		return "home";

	}
	
	@RequestMapping("/form")
	public String form(Model model, Input input) {
		model.addAttribute("title", "健康者一覧ページ");
		List<Ent> list = entformdao.searchDb();
		model.addAttribute("dbList", list);

		return "form";

	}
	@RequestMapping("/batform")
	public String batform(Model model, Input input) {
		model.addAttribute("title", "再診者一覧ページ");
		List<Ent> list2 = entformdao2.searchDb();
		model.addAttribute("dbList2", list2);

		return "batform";

	}
	@RequestMapping("/add")
	public String add(Model model, Input input) {
		model.addAttribute("title", "入力ページ");
//		List<Ent> list = entformdao.searchDb();
//		model.addAttribute("dbList", list);

		return "add";

	}
	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("title", "入力ページ");
			return "add";
		}

		model.addAttribute("title", "確認ページ");
		return "confirm";
	}

	@RequestMapping("/complete")
	public String complete(Model model, Input input, Long id) {
		model.addAttribute("title", "完了ページ");
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

		entformdao.insertDb(entform);

		return "complete";

	}
	@RequestMapping("/complete2")
	public String complete2(Model model, Input input) {
		model.addAttribute("title", "完了ページ");
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

		
		entformdao2.insertDb(entform);

		return "complete";

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
		
		@RequestMapping("/cha/{id}")
		public String change(@PathVariable Long id, Model model) {
			//DBからデータを1件取ってくる(リストの形)
			List<Ent> list = entformdao.selectOne(id);
			
			//リストから、オブジェクトだけをピックアップ
			Ent entformdb = list.get(0);

			//スタンバイしているViewに向かって、データを投げる
			model.addAttribute("input", entformdb);
			model.addAttribute("title", "判定更新ページ");

			return "change";
		}
		@RequestMapping("/cha/{id}/exe")
		public String changeExe(@PathVariable  Long id, Input input) {
			
			//フォームの値をエンティティに入れ直し
			
			Ent entform = new Ent();
			
			String health = "異常あり";

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
			
			//フォームの値をエンティティに入れ直し
			if (result.hasErrors()) {
				model.addAttribute("title", "入力ページ");
				return "add";
			}
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
		public String editExe2(@PathVariable Long id,Model model, Input input) {

			
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
			
			//更新の実行
			entformdao2.updateDb(id, entform);

			//一覧画面へリダイレクト
			return "redirect:/batform";
		}

}

